/**
 * Author : chizf
 * Date : 2020年3月2日 下午5:28:08
 * Title : org.mbs.server.MbsApplication.java
 *
**/
package org.mbs.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.socket.config.annotation.EnableWebSocket;

@SpringBootApplication(scanBasePackages="org.mbs.server")
@EnableWebSocket
public class MbsApplication {
	public static void main(String[] args) {
        SpringApplication.run(MbsApplication.class, args);
    }
}
