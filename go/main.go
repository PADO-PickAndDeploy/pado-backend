package main

import (
	"log"
)

func main() {
	err := grpc.StartServer(":50051")
	if err != nil {
		log.Fatalf("failed to start gRPC server: %v", err)
	}
}
