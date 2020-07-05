
速度未测试，网上了解
### json  JSONObject  java
1.对复杂类型解析并不友好，多个依赖包

2.基本就是JSONObject(impl Map )与JSONArray(impl List)

3.处理速度慢
### gson Gson   google
1.对复杂类型有很好的支持，一个依赖包

2.各种注解自定义处理各种需求 ， 有自己的配置类GsonBuilder，
可以自定义属性，自定义策略，自定义序列反序列解析

3.慢于fastjson
### jackson  ObjectMapper  public
1.对复杂类型有很好的支持，多个依赖包，扩展很强

2.各种注解自定义处理各种需求，注解比gson还多， 
有自己的配置类ObjectMapper 可以自定义属性，
自定义策略，自定义序列反序列解析

3.慢于fastjson

### fastjson  JSON  ali
1.对复杂类型有较好的支持，一个依赖包

2.各种注解自定义处理各种需求， 
有自己的配置类SerializerFeature可以自定义属性，
自定义策略，自定义序列反序列解析

3.最快解析

## 总结
解析推荐使用Gson , 如果对速度有要求可使用fastJson + Gson组合
如果是特殊场景需求可考虑jackson

fastjson toJson  se
{"age":1,"birthday":"2020-07-05 21:32:57","dataList":[{"id":111111111111111,"subage":1,"subbirthday":"2020-07-05 21:32:57","submoney":11.66,"subname":"1"}],"dataMap":{"1":{"$ref":"$.dataList[0]"}},"id":1234567890013213,"money":12.33,"name":"李四"}

FASTJSON parse  dese
JsonBO(id=1234567890013213, name=李四, age=1, money=12.33, birthday=Sun Jul 05 21:37:59 CST 2020, dataList=[JsonBO.JsonSubBO(id=111111111111111, subname=1, subage=1, submoney=11.66, subbirthday=Sun Jul 05 21:38:00 CST 2020)], dataMap={1=JsonBO.JsonSubBO(id=111111111111111, subname=1, subage=1, submoney=11.66, subbirthday=Sun Jul 05 21:38:00 CST 2020)})

GSON   dese
JsonBO(id=1234567890013213, name=李四, age=1, money=12.33, birthday=Sun Jul 05 21:37:59 CST 2020, dataList=[JsonBO.JsonSubBO(id=111111111111111, subname=1, subage=1, submoney=11.66, subbirthday=Sun Jul 05 21:38:00 CST 2020)], dataMap={1=JsonBO.JsonSubBO(id=null, subname=null, subage=null, submoney=null, subbirthday=null)})

