CodeSharedFL

How to run application with Human intervention

1: Run TestApplication
2: Two Controllers
    a: Currency Controller
        Conversion between two currencies from to to.
        Hitting to this end point(https://api.apilayer.com/exchangerates_data/convert?to=GBP&from=JPY&amount=25) and fetch the result in JSON.
        Input  -  localhost:8080/api/v1/exchange_amount/from/USD/to/GBP/amount/100
        Output -
                {
                "from": "JPY",
                "to": "GBP",
                "to_amount": 25,
                "exchange_rate": 0.006139,
                "result": 0.153475,
                "repute": true,
                "status": 201,
                "message": "Conversion Done",
                "description": "Conversion done"
                }
    b: Validation Controller
        Having Service and isolated function which performs validations for input.
        Input - localhost:8080/v1/validate_ssn/131052-308T
        Output -
                {
                "repute": true,
                "status": 201,
                "message": "Validation status : true"
                }
            

How to Run in Docker Container.
    Attached is docker file to run it in docker containers with JRE11.

Postman Collection
    Attached is the postman collection for reference.