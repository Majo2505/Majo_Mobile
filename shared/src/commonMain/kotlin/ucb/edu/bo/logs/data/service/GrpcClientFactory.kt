package ucb.edu.bo.logs.data.service

import com.squareup.wire.GrpcClient

// Cada plataforma crea su cliente gRPC
expect fun createGrpcClient(baseUrl: String): GrpcClient