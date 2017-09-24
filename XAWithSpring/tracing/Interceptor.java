package tracing.aop;
import javax.transaction.xa.XAResource;
import javax.transaction.TransactionManager;
import javax.transaction.UserTransaction;
//import org.springframework.transaction.jta.JtaTransactionManager;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public aspect Interceptor {

	private static final Log log = LogFactory.getLog(Interceptor.class);

	pointcut xaCalls() : call(*  XAResource.*(..)) 
		                 || call(*  TransactionManager.*(..))
		                 || call(*  UserTransaction.*(..))
		                 ;

	Object around() : xaCalls() {
		log.debug("XA CALL -> This: " + thisJoinPoint.getThis());
		log.debug("       -> Target: " + thisJoinPoint.getTarget());
		log.debug("       -> Signature: " + thisJoinPoint.getSignature());
		Object[] args = thisJoinPoint.getArgs();
		StringBuffer str = new StringBuffer(" ");
		for(int i=0; i< args.length; i++) {
			str.append(" [" + i + "] = " + args[i]);
		}
		log.debug(str);
		Object obj = proceed();
		log.debug("XA CALL RETURNS-> " + obj);
		return obj;
	}

/*
	before() : xaCalls() {
		System.out.println("XA CALL-> This: " + thisJoinPoint.getThis());
		System.out.println("       -> Target: " + thisJoinPoint.getTarget());
		System.out.println("       -> Signature: " + thisJoinPoint.getSignature());
		Object[] args = thisJoinPoint.getArgs();
		StringBuffer str = new StringBuffer(" ");
		for(int i=0; i< args.length; i++) {
			str.append(" [" + i + "] = " + args[i]);
		}
		System.out.println(str);
	}
*/

/*
	around() : xaCalls() {
		System.out.println("XA CALL -> This: " + thisJoinPoint.getThis());
		System.out.println("       -> Target: " + thisJoinPoint.getTarget());
		System.out.println("       -> Signature: " + thisJoinPoint.getSignature());
		Object[] args = thisJoinPoint.getArgs();
		StringBuffer str = new StringBuffer(" ");
		for(int i=0; i< args.length; i++) {
			str.append(" [" + i + "] = " + args[i]);
		}
		System.out.println(str);
		Object obj = proceed();
		System.out.println("XA CALL RETURNS-> " + obj);
	}
*/

}
