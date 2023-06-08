package Atlas.r

import kotlin.random.Random
import kotlin.random.asJavaRandom

private class Random {
    init {
        Random.apply {
            this.nextInt(50,100) // from 50 until 100
            this.nextLong()
            this.nextDouble()
            this.nextFloat()
            this.nextBoolean() // Random true or false.
            this.nextBits(0) // return random number from binary by bitCount as max range.
            this.nextBytes(10) /** return [Array] of random numbers in byte range */
            this.asJavaRandom().apply {
                this.doubles().run {
                    /** @see AtlasJ.JStream */
                }
                this.ints().run {
                    /** @see AtlasJ.JStream */
                }
                this.longs().run {
                    /** @see AtlasJ.JStream */
                }
                this.nextGaussian() // return random double number between 0.0 and 1.0
                this.setSeed(1L) // TODO: 2/5/21 NOT REPORTED!
                this.nextBoolean() // And other types.
            }
        }
    }
}