package com.crytpo.shibwhalealerts.service.model.data

data class DataModel(
    var message: String, // OK
    var result: List<Result>,
    var status: String // 1
) {
    data class Result(
        var blockHash: String, // 0x973324f8cdbbcb14440ca507fae787b92131cd447d38b669a9e23ca80ef431aa
        var blockNumber: String, // 13687259
        var confirmations: String, // 2
        var contractAddress: String, // 0x95ad61b0a150d79219dcf64e1e6cc01f0b64c4ce
        var cumulativeGasUsed: String, // 25312542
        var from: String, // 0xf4160279af0b5857104e8ac1867ff41f67fce5fe
        var gas: String, // 76019
        var gasPrice: String, // 94921839580
        var gasUsed: String, // 75811
        var hash: String, // 0x60c222739ce923ac001c3dcfa8214603178488e150d2050db67ef560135d2d4b
        var input: String, // deprecated
        var nonce: String, // 10
        var timeStamp: String, // 1637895430
        var to: String, // 0xb4a81261b16b92af0b9f7c4a83f1e885132d81e4
        var tokenDecimal: String, // 18
        var tokenName: String, // SHIBA INU
        var tokenSymbol: String, // SHIB
        var transactionIndex: String, // 375
        var value: String // 110000000000000000000000000
    )
}