see: http://spring-guides.marcuschiu.com/spring-cloud/cloud-config-and-discovery/cloud-consul-discovery/spring-cloud-consul-discovery.html

# Run Consul Server
```shell
docker run \ 
  --name consul-server \
  -p 8500:8500 \ 
  -p 8600:8600/udp \ 
  hashicorp/consul \ 
  agent -server \ 
  -bootstrap-expect=1 \ 
  -ui -client=0.0.0.0
```

goto `http://localhost:8500`

# Run Application
```shell
mvn spring-boot:run
```

goto `http://localhost:8500` and verify `myApp` was registered