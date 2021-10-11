package local.sandbox

import local.test.BlockingTest
import org.junit.jupiter.api.Test

class MainTest : BlockingTest {
  @Test
  fun test() = blockingTest {
    main()
  }
}
