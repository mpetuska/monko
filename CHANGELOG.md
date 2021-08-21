# v0.0.2
## Versions
* [mongo-java-driver@4.3.0](https://github.com/mongodb/mongo-java-driver/tree/r4.3.0)
* [node-mongodb-native@4.0.0](https://github.com/mongodb/node-mongodb-native/tree/v4.0.0)
* [mongo-c-driver@1.8.0](https://github.com/mongodb/mongo-c-driver/tree/1.18.0)

## Changes
* Initial API structure unlocking proxies into native objects for each platform
* `MonkoClient::database` implementation
* `MonkoDatabase::runCommand` implementation
* `MonkoDatabase::collection` implementation
* `MonkoCollection::count` implementation
* `MonkoBson` implementation and two-way conversions from/to string
* GH Actions adjusted to work with native `libmongoc`
