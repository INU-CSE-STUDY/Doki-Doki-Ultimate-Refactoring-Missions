package com.capstone.webserver.common.util.db;

import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import jakarta.annotation.PreDestroy;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.Properties;

@Slf4j
@Profile({"dev", "test"})
@Component
@ConfigurationProperties(prefix = "ssh")
@Setter
public class SshTunnelingInitializer {
    @Value("${ssh.remote_jump_host}") private String remoteJumpHost;
    @Value("${ssh.user}") private String user;
    @Value("${ssh.ssh_port}") private int sshPort;
    @Value("${ssh.private_key}") private String privateKey;
    @Value("${ssh.remote_host}") private String remoteHost;
    @Value("${ssh.database_port}") private int databasePort;

    private Session session;

    @PreDestroy
    public void closeSSH() {
        if (session != null && session.isConnected())
            session.disconnect();
    }

    public Integer buildSshConnection() {

        Integer forwardedPort = null;

        try {
            log.info("{}@{}:{}:{} with privateKey",user, remoteJumpHost, sshPort, databasePort);

            log.info("start ssh tunneling..");
            JSch jSch = new JSch();

            log.info("creating ssh session");

            jSch.addIdentity(privateKey);

            session = jSch.getSession(user, remoteJumpHost, sshPort);
            Properties config = new Properties();
            config.put("StrictHostKeyChecking", "no");
            session.setConfig(config);
            log.info("complete creating ssh session");

            log.info("start connecting ssh connection");
            session.connect();
            log.info("success connecting ssh connection ");

            log.info("start forwarding");
            forwardedPort = session.setPortForwardingL(0, remoteHost, databasePort);
            log.info("successfully connected to database");
        } catch (JSchException e){
            this.closeSSH();
            e.printStackTrace();
            log.error("fail to make ssh tunneling : {}", e.getMessage());
        }

        return forwardedPort;
    }
}
