# simple-nc
넷캣(Netcat) TCP Server/Client Program

## Usage
> snc [option] [hostname] [port]  
Options:  
> -l <port>  서버모드로 동작, 입력받은 포트로 listen
### 서버 모드로 동작
> snc -l 12345

### 클라이언트 모드로 동작
> snc 127.0.0.1 12345
