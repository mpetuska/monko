# v0.0.1
## Versions
* kotlin@1.5.31
* [mongo-java-driver@4.3.3](https://github.com/mongodb/mongo-java-driver/tree/r4.3.3)
* [node-mongodb-native@^4.0.0](https://github.com/mongodb/node-mongodb-native/tree/v4.0.0)
* [mongo-c-driver@1.19.1](https://github.com/mongodb/mongo-c-driver/tree/1.19.1)

## Changes
* Initial API structure unlocking proxies into native objects for each platform
* `MonkoClient::database` implementation
* `MonkoDatabase::runCommand` implementation
* `MonkoDatabase::collection` implementation
* `MonkoCollection::count` implementation
* `MonkoBson` implementation and two-way conversions from/to string
* GH Actions adjusted to work with native `libmongoc`
