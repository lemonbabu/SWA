package com.crytpo.shibwhalealerts.service.model.data

data class NormalTxDataModel(
    var message: String, // OK
    var result: List<Result>,
    var status: String // 1
) {
    data class Result(
        var blockHash: String, // 0xa756c0ee258647b0cb2d3a697ee461a4e40d2cce759b3598a9a9ebee88db51bd
        var blockNumber: String, // 13731416
        var confirmations: String, // 1
        var contractAddress: String,
        var cumulativeGasUsed: String, // 16207542
        var from: String, // 0x95a9bd206ae52c4ba8eecfc93d18eacdd41c88cc
        var gas: String, // 250000
        var gasPrice: String, // 98406004213
        var gasUsed: String, // 51907
        var hash: String, // 0x37c762345d769e1288e9d0453d94fccebfff5308ceb1856bdc0f0ce15e2d3c91
        var input: String, // 0xa9059cbb00000000000000000000000024af5c96d0b1b97f4f7d4c02e4d21a8c23d12c500000000000000000000000000000000000000000000c0f9adb72b5aa21067400
        var isError: String, // 0
        var nonce: String, // 602391
        var timeStamp: String, // 1638504410
        var to: String, // 0x95ad61b0a150d79219dcf64e1e6cc01f0b64c4ce
        var transactionIndex: String, // 174
        var txreceipt_status: String, // 1
        var value: String // 0
    )
}