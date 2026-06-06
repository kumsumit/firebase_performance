// swift-tools-version: 5.9
// The swift-tools-version declares the minimum version of Swift required to build this package.
//
// Generated file. Do not edit.
//

import PackageDescription

let package = Package(
    name: "FlutterGeneratedPluginSwiftPackage",
    platforms: [
        .iOS("13.0")
    ],
    products: [
        .library(name: "FlutterGeneratedPluginSwiftPackage", type: .static, targets: ["FlutterGeneratedPluginSwiftPackage"])
    ],
    dependencies: [
        .package(name: "integration_test", path: "../.packages/integration_test"),
        .package(name: "firebase_performance", path: "../.packages/firebase_performance-d9c564215f1a6aeabb71d06d422dbdab213d1c81"),
        .package(name: "firebase_core", path: "../.packages/firebase_core-c2c52b176464f36ba86126c6c3eddf51a4a51942"),
        .package(name: "FlutterFramework", path: "../.packages/FlutterFramework")
    ],
    targets: [
        .target(
            name: "FlutterGeneratedPluginSwiftPackage",
            dependencies: [
                .product(name: "integration-test", package: "integration_test"),
                .product(name: "firebase-performance", package: "firebase_performance"),
                .product(name: "firebase-core", package: "firebase_core"),
                .product(name: "FlutterFramework", package: "FlutterFramework")
            ]
        )
    ]
)
