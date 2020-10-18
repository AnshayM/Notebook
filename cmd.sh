systemctl stop notebook
mv /home/test/notebook.jar /home/test/bak/notebook.`date +%Y%m%d`.jar
mv ./target/notebook-0.jar /home/test/notebook.jar
systemctl start notebook