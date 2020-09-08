/*package io.github.anantharajuc.sbtest.auditing;

import org.springframework.boot.actuate.audit.AuditEvent;
import org.springframework.boot.actuate.audit.listener.AuditApplicationEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Component
public class LoginAttemptsLogger 
{
	@EventListener
    public void auditEventHappened(AuditApplicationEvent auditApplicationEvent) 
	{
        AuditEvent auditEvent = auditApplicationEvent.getAuditEvent();
        
        log.info("Principal " + auditEvent.getPrincipal() + " - " + auditEvent.getType());

        WebAuthenticationDetails details = (WebAuthenticationDetails) auditEvent.getData().get("details");
        
        log.info("  Remote IP address: " + details.getRemoteAddress());
        log.info("  Session Id: " + details.getSessionId());
        log.info("  Request URL: " + auditEvent.getData().get("requestUrl"));
    }
}*/
