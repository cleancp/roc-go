fastjson toJson  se
{"age":1,"birthday":"2020-07-05 21:32:57","dataList":[{"id":111111111111111,"subage":1,"subbirthday":"2020-07-05 21:32:57","submoney":11.66,"subname":"1"}],"dataMap":{"1":{"$ref":"$.dataList[0]"}},"id":1234567890013213,"money":12.33,"name":"李四"}

FASTJSON parse  dese
JsonBO(id=1234567890013213, name=李四, age=1, money=12.33, birthday=Sun Jul 05 21:37:59 CST 2020, dataList=[JsonBO.JsonSubBO(id=111111111111111, subname=1, subage=1, submoney=11.66, subbirthday=Sun Jul 05 21:38:00 CST 2020)], dataMap={1=JsonBO.JsonSubBO(id=111111111111111, subname=1, subage=1, submoney=11.66, subbirthday=Sun Jul 05 21:38:00 CST 2020)})

GSON   dese
JsonBO(id=1234567890013213, name=李四, age=1, money=12.33, birthday=Sun Jul 05 21:37:59 CST 2020, dataList=[JsonBO.JsonSubBO(id=111111111111111, subname=1, subage=1, submoney=11.66, subbirthday=Sun Jul 05 21:38:00 CST 2020)], dataMap={1=JsonBO.JsonSubBO(id=null, subname=null, subage=null, submoney=null, subbirthday=null)})

