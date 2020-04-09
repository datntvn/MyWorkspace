# # QueryDSL sample code!

Version QueryDSL used: 4.2.1

NOTE: QueryDSL is abandoned --> consider carefully before actually using this library

https://github.com/querydsl/querydsl/issues/2456


## Updated on Apr 9th, 2020
Limitation of QueryDSL
When using library querydsl-jpa then
- not able to alias on column name
- Related to: https://github.com/querydsl/querydsl/issues/2558

When using library querydsl-sql, then
- not able to join, innerjoin, leftjoin, ... on composite keys that has been configured with @Embeddable, and @EmbeddedId

***Able to use "querydsl-jpa" to write a complicated query, where use:***
- ***case when***
- ***left join***
- ***composite key on @EmbeddedId, and @Embeddable***
***But still limited , because, cannot use a list of dynamic Predicate. in order to use a list of dynamic Predicate, then we must set alias at columns to be select.***
***But "querydsl-jpa" is not able to use ".as".***
***".as" is a feature of "querydsl-sql"***

NOTE: file "queryDSL_dynamicPredicates.zip" us a project that is generated from command:
```
openapi-generator generate -i <Your OAS YML file> -c generator-config.json -g spring

