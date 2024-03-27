package com.example.unscramble

import kotlin.random.Random

class WordRepository(level: String) {
    private val randomWords: Array<String> = arrayOf("animal", "auto", "anecdote", "alphabet", "all", "awesome",
        "arise", "balloon", "basket", "bench", "best", "birthday", "book", "briefcase", "camera",
        "camping", "candle", "cat", "cauliflower", "chat", "children", "class", "classic", "classroom",
        "coffee", "colorful", "cookie", "creative", "cruise", "dance", "daytime", "dinosaur", "doorknob", "dine", "dream",
        "dusk", "eating", "elephant", "emerald", "eerie", "electric", "finish", "flowers", "follow", "fox",
        "frame", "free", "frequent", "funnel", "green", "guitar", "grocery", "glass", "great", "giggle",
        "haircut", "half", "homemade", "happen", "honey", "hurry", "hundred", "ice", "igloo", "invest",
        "invite", "icon", "introduce", "joke", "jovial", "journal", "jump", "join", "kangaroo", "keyboard",
        "kitchen", "koala", "kind", "kaleidoscope", "landscape", "late", "laugh", "learning", "lemon", "letter",
        "lily", "magazine", "marine", "marshmallow", "maze", "meditate", "melody", "minute", "monument",
        "moon", "motorcycle", "mountain", "music", "north", "nose", "night", "name", "never", "negotiate", "number",
        "opposite", "octopus", "oak", "order", "open", "polar", "pack", "painting", "person", "picnic",
        "pillow", "pizza", "podcast", "presentation", "puppy", "puzzle", "recipe", "release", "restaurant",
        "revolve", "rewind", "room", "run", "secret", "seed", "ship", "shirt", "should", "small", "spaceship",
        "stargazing", "skill", "street", "style", "sunrise", "taxi", "tidy", "timer", "together", "tooth",
        "tourist", "travel", "truck", "under", "useful", "unicorn", "unique", "uplift", "uniform", "vase",
        "violin", "visitor", "vision", "volume", "view", "walrus", "wander", "world", "winter", "well", "whirlwind",
        "x-ray", "xylophone", "yoga", "yogurt", "yoyo", "you", "year", "yummy", "zebra", "zigzag", "zoology",
        "zone", "zeal")

    private val easyLevel = arrayOf("animal", "auto", "all", "arise", "basket", "bench", "best", "book", "camera", "candle", "cat",
        "chat", "class", "coffee", "cookie", "cruise", "dance", "dine", "dream", "dusk", "eating",
        "eerie", "finish", "follow", "fox", "frame", "free", "funnel", "green", "guitar", "glass",
        "great", "giggle", "half", "happen", "honey", "hurry", "ice", "igloo", "invest", "invite",
        "icon", "joke", "jovial", "jump", "join", "koala", "kind", "late", "laugh", "lemon", "letter",
        "lily", "marine", "maze", "melody", "minute", "moon", "music", "north", "nose", "night", "name",
        "never", "number", "oak", "order", "open", "polar", "pack", "person", "picnic", "pillow",
        "pizza", "puppy", "puzzle", "recipe", "rewind", "room", "run", "secret", "seed", "ship",
        "shirt", "should", "small", "skill", "street", "style", "taxi", "tidy", "timer", "tooth",
        "travel", "truck", "under", "useful", "unique", "uplift", "vase", "violin", "vision", "volume",
        "view", "walrus", "wander", "world", "winter", "well", "x-ray", "yoga", "yogurt", "yoyo", "you",
        "year", "yummy", "zebra", "zigzag", "zone", "zeal")

    private val mediumLevel = arrayOf("anecdote", "alphabet", "awesome", "balloon", "birthday", "camping", "children", "classic",
        "colorful", "creative", "daytime", "dinosaur", "doorknob", "elephant", "emerald", "electric",
        "flowers", "frequent", "grocery", "haircut", "homemade", "hundred", "journal", "kangaroo",
        "keyboard", "kitchen", "learning", "magazine", "meditate", "monument", "mountain", "opposite",
        "octopus", "painting", "podcast", "release", "revolve", "sunrise", "together", "tourist",
        "unicorn", "uniform", "visitor", "zoology")

    private val hardLevel = arrayOf("briefcase", "cauliflower", "classroom", "introduce", "kaleidoscope", "landscape",
        "marshmallow", "motorcycle", "negotiate", "presentation", "restaurant", "spaceship",
        "stargazing", "whirlwind", "xylophone")

    private val words = when(level){
            "easy" -> easyLevel
            "medium" -> mediumLevel
            "hard" -> hardLevel
            else -> randomWords
        }

    private var wordCount = 0


    private fun randomize(string: String): String{
        val arrayChar = string.toCharArray()
        val random = Random
        for(i in arrayChar.indices){
            val randomIndex = random.nextInt(arrayChar.size)

            val temp = arrayChar[i]
            arrayChar[i] = arrayChar[randomIndex]
            arrayChar[randomIndex] = temp
        }
        val randomString = String(arrayChar)
        return if(randomString != string)
            randomString
        else
            randomize(string)
    }

    private fun getRandomWord(): String{
        val randomIndex = Random.nextInt(words.size)
        this.wordCount++
        if(this.wordCount == 10)
            this.gameOver()
        return words[randomIndex]
    }

    private fun gameOver() {
        this.wordCount = 0
    }


    fun getRepository(): List<String>{

        val randomWord = this.getRandomWord()
        return listOf(randomWord, this.randomize(randomWord))
    }

}
