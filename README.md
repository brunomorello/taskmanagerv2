# taskmanagerv2

docker run -p 8080:8080 -e SPRING_PROFILES_ACTIVE='prod' taskamanger/api

docker run -p 8080:8080 -e SPRING_PROFILES_ACTIVE='prod' -e DATABASEURL='' -e DATABASE_USERNAME='root' -e DATABASE_PASSWORD='dqm50vnc' -e JWT_SECRET='' taskamanger/api