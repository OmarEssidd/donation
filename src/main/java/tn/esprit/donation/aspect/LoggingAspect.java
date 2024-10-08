package tn.esprit.donation.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint; // Correction ici
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Slf4j
public class LoggingAspect {

    @AfterReturning("execution(* tn.esprit.donation.services.ServiceIMP.addDon(..))")
    public void logMethodEntry(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        log.info("Méthode {} a été appelée. Merci pour ce don!", methodName); // Message amélioré
    }
}
