# Simple App Demo

This app shows a list of studnets by making a network call and showing it a single screen 


The design approach was to use MVVM to brake up the application into a small number of well defined classes with specific responsibilities.

##### Few of the components and libraries used included:
1. [Dagger Hilt:](https://dagger.dev/hilt/) - Dependency Injection
2. [Retrofit:](https://square.github.io/retrofit/) - Network Calls and Offline Caching 
4. [Coroutine:](https://developer.android.com/kotlin/coroutines?gclid=CjwKCAjw6eWnBhAKEiwADpnw9reqayIsNbeY4j-feUQ0E2LPCoCOHT0w2QQjii5_uiMxzKD8CTrbuRoCsxkQAvD_BwE&gclsrc=aw.ds) - Concurrency and cooperative multitasking
5. [Flow:](https://developer.android.com/kotlin/flow) - Process data stream asynchronously
6. [Mockito:](https://site.mockito.org/) - Unit testing
7. [Espresso:](https://developer.android.com/training/testing/espresso) - UI testing
8. [LeakCanary:](https://square.github.io/leakcanary/) - Memory leak detection

Improvements
1. Using a Room and android's paging library for data persistence, paging and to setup caching.
2. Using fragments
