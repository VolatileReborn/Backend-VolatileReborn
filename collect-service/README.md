
# Run Locally
1. Build:

   ```sh
   mvn  clean package jacoco:report -Dmaven.test.failure.ignore=true
   docker build -t lyklove/backend_volatile_reborn:latest  . 
   ```

2. Run:

   ```sh
   docker container run --name backend_volatile_reborn --net=host -d lyklove/backend_volatile_reborn:latest
   ```

