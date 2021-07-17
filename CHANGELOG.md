# v0.0.1
## Versions
* [mongo-java-driver@4.2.3](https://github.com/mongodb/mongo-java-driver/tree/r4.2.3)
* [node-mongodb-native@3.6.10](https://github.com/mongodb/node-mongodb-native/tree/v3.6.10)
* [mongo-c-driver@1.17.7](https://github.com/mongodb/mongo-c-driver/tree/1.17.7)

## Changes
* Initial API structure unlocking proxies into native objects for each platform
* `MonkoClient::database` implementation
* `MonkoDatabase::runCommand` implementation
* `MonkoDatabase::collection` implementation
* `MonkoCollection::count` implementation
* `MonkoBson` implementation and two-way conversions from/to string
* GH Actions adjusted to work with native `libmongoc`
