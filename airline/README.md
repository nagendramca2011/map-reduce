# Hadoop
## Author: Nagendra Padamara



[root@localhost word-count-test]# ls -al
total 24
drwxr-xr-x 2 root     root     4096 Jun 10 05:18 .
drwxrwxr-x 7 cloudera cloudera 4096 Jun 10 04:51 ..
-rw-r--r-- 1 root     root      212 Jun 10 05:18 input.txt
-rw-r--r-- 1 root     root      212 Jun 10 05:18 input.txt~
-rw-r--r-- 1 root     root     5946 Jun 10 04:59 MapReduce-0.0.1-SNAPSHOT.jar
-rw-r--r-- 1 root     root        0 Jun 10 05:18 output.txt
[root@localhost word-count-test]# chmod +x input.txt
[root@localhost word-count-test]# ls -al
total 24
drwxr-xr-x 2 root     root     4096 Jun 10 05:18 .
drwxrwxr-x 7 cloudera cloudera 4096 Jun 10 04:51 ..
-rwxr-xr-x 1 root     root      212 Jun 10 05:18 input.txt
-rw-r--r-- 1 root     root      212 Jun 10 05:18 input.txt~
-rw-r--r-- 1 root     root     5946 Jun 10 04:59 MapReduce-0.0.1-SNAPSHOT.jar
-rw-r--r-- 1 root     root        0 Jun 10 05:18 output.txt
[root@localhost word-count-test]# chmod +x output.txt 
#--
[cloudera@localhost word-count-test]$ hadoop jar MapReduce-0.0.1-SNAPSHOT.jar wow.hadoop.MapReduce.WordCount input.txt output.txt
16/06/10 05:33:04 WARN mapred.JobClient: Use GenericOptionsParser for parsing the arguments. Applications should implement Tool for the same.
16/06/10 05:33:05 INFO mapred.JobClient: Cleaning up the staging area hdfs://localhost.localdomain:8020/user/cloudera/.staging/job_201606092123_0001
16/06/10 05:33:05 ERROR security.UserGroupInformation: PriviledgedActionException as:cloudera (auth:SIMPLE) cause:org.apache.hadoop.mapreduce.lib.input.InvalidInputException: Input path does not exist: hdfs://localhost.localdomain:8020/user/cloudera/input.txt
Exception in thread "main" org.apache.hadoop.mapreduce.lib.input.InvalidInputException: Input path does not exist: hdfs://localhost.localdomain:8020/user/cloudera/input.txt
	at org.apache.hadoop.mapreduce.lib.input.FileInputFormat.listStatus(FileInputFormat.java:231)
	at org.apache.hadoop.mapreduce.lib.input.FileInputFormat.getSplits(FileInputFormat.java:248)
	at org.apache.hadoop.mapred.JobClient.writeNewSplits(JobClient.java:1107)
	at org.apache.hadoop.mapred.JobClient.writeSplits(JobClient.java:1124)
	at org.apache.hadoop.mapred.JobClient.access$600(JobClient.java:178)
	at org.apache.hadoop.mapred.JobClient$2.run(JobClient.java:1023)
	at org.apache.hadoop.mapred.JobClient$2.run(JobClient.java:976)
	at java.security.AccessController.doPrivileged(Native Method)
	at javax.security.auth.Subject.doAs(Subject.java:422)
	at org.apache.hadoop.security.UserGroupInformation.doAs(UserGroupInformation.java:1438)
	at org.apache.hadoop.mapred.JobClient.submitJobInternal(JobClient.java:976)
	at org.apache.hadoop.mapreduce.Job.submit(Job.java:582)
	at org.apache.hadoop.mapreduce.Job.waitForCompletion(Job.java:612)
	at wow.hadoop.MapReduce.WordCount.main(WordCount.java:59)
	at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
	at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)
	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
	at java.lang.reflect.Method.invoke(Method.java:498)
	at org.apache.hadoop.util.RunJar.main(RunJar.java:208)

#--

It means we should place input and output folders in hdfs
# Created a folder in HDFS
[cloudera@localhost word-count-test]$ hadoop fs -mkdir mvn-wordcount

# Added input and output files
[cloudera@localhost word-count-test]$ hadoop fs -put '/home/cloudera/Desktop/MapReduceProgs/MapReduce/word-count-test/input.txt' mvn-wordcount
[cloudera@localhost word-count-test]$ hadoop fs -put '/home/cloudera/Desktop/MapReduceProgs/MapReduce/word-count-test/output.txt' mvn-wordcount



[cloudera@localhost word-count-test]$ hadoop jar MapReduce-0.0.1-SNAPSHOT.jar wow.hadoop.MapReduce.WordCount '/user/cloudera/mvn-wordcount/input.txt' '/user/cloudera/mvn-wordcount/output.txt' 


# how to give execute permisssion for folder
[root@localhost Desktop]# chmod 777 mvn-mapreduce-projects/

[cloudera@localhost MapReduce]$ hadoop fs -chmod 777 /user/cloudera/mvn-wordcount
[cloudera@localhost MapReduce]$ hadoop fs -ls



