
The project contains the following directories:
-----------------------------------------------
.settings     - for eclipse
bin           - class files directory
configs       - all the configuration related files reside here
lib           - all the jar files reside here
original-libs - just make sure the original jar files are kept here before weaving,
                otherwise this should be empty.
sql           - the Mysql SQL code to create our test databases
src           - source code for the use cases. 
                Main class is MainApp.java.
                EventHandler.java is used for usecase1 and usecase2
                MessageHandler.java is the MDPOJO used for usecase3
                MessageSequenceDAO.java is the Spring based DAO class.
                MySql.java is the wrapper class for working with JBossTS and MySql.
tracing       - the Interceptor aspectj code to trace XA calls


To run the samples in the project please use Ant tool along with the build.xml file.

NOTE on messaging provider jar files:
------------------------------------
Open the build.xml file and look at the dependent libraries and make sure the
libraries for your messaging provider are inlcuded there. For this article,
we used both ActiveMQ and TIBCO EMS messaging. 
TIBCO EMS:
----------
EMS needs "tibjms.jar" jar file and is not available as part of this download, 
since it is commercial. 

ActiveMQ:
---------
For ActiveMQ all the required jar files are included (ver 4.1.1). So make
sure you have downloaded ActiveMQ 4.1.1 and installed it on your machine.

After installing add the following piece of code to the activemq.xml file 
in the conf directory:

    <destinations>
      <queue physicalName="test.q1" />
    </destinations>

If you are not connected to a network, please remove all the elements which
refet to the string "multicast:" in the activemq.xml, otherwise you will notice
errors while starting.



Weaving JTA runtime libraries using AspectJ
-------------------------------------------
To weave aspectj code on the JTA libraries we are using, please make sure
that the "aspectjtools.jar" library is under the ANT lib directory. Once 
it is in place you can use the weave tasks in the build.xml file and then
track the XA related calls. 

**When weaving JBossTS, it requires the classes12.jar from Oracle which is 
not included in the project, please inlcude that jar and run the task again.


Note on log file clean up:
--------------------------
Please make sure the log files are cleaned-up especially when Bitronix is used
when testing a rollback condition. Even when the application is closed with a
CTLR-C option, the log files are locked and one has to manually delete these files
for both Atomikos and Bitronix. For Bitronix you, for testing purposes, the 
bitronix.tm.journal.disk.skipCorruptedLogs=true is used in the properties file, and 
this should not be used in production.

RUNNING Use Cases:
------------------

To run USE CASE1 do the following:
1.   Change to the project directory.
2.   At the command prompt type "ant build" to build the project.
3.   At the command prompt type "ant JbossSender -Dtest_mode=fail".This will make sure the code 
     throws a runtime exception resulting in a rollback.
4.   To test a successful update, type "ant JbossSender _Dtest_mode=success"
     This will result in the values to be updated in both the databases under one global transaction.
5.   To stop the server type "shutdown"

To run USE CASE2 do the following:
1.   Change to the project directory.
2.   At the command prompt, type "ant build" to build the project.
3.   Type "ant AtomikosSender-AMQ -Dtest_mode=fail" to test the rollback functionality and 
      type "ant BitronixSender-AMQ -Dtest_mode=fail" for Bitronix.
4.   To test the successful scenario (commit), 
      type "ant AtomikosSender-AMQ _Dtest_mode=success" for Atomikos and 
      type "ant BitornixSender-AMQ -Dtest_mode=success" for Bitronix.
5.   To stop the server, type "shutdown"

To run USE CASE3 do the following:
1.   Change to the project directory.
2.   At the command prompt type "ant build" to build the project.
3.   At the command prompt type "ant AtomikosConsumer-AMQ -Dtest_mode=fail" to test
     the rollback functionality. 
     To run with Bitronix, type "ant BitronixConsumer=AMQ -Dtest_mode=fail".
4.   To test the successful scenario (commit), 
      type "ant AtomikosConsumer-AMQ _Dtest_mode=success" for Atomikos and type
      "ant BitornixConsumer-AMQ -Dtest_mode=success" for Bitronix.
5.   To stop the server type "shutdown"







