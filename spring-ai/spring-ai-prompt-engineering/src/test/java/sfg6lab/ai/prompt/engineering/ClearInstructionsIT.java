//: sfg6lab.ai.prompt.engineering.ClearInstructionsIT.java

package sfg6lab.ai.prompt.engineering;


import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.ai.autoconfigure.openai.OpenAiChatProperties;
import org.springframework.ai.chat.messages.Message;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.chat.prompt.SystemPromptTemplate;
import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import static org.junit.jupiter.api.TestInstance.Lifecycle.PER_CLASS;


/*
 * Using AI to infer the sentiment of a review or topic
 */
@TestInstance(PER_CLASS)
class ClearInstructionsIT extends BasePromptEngineeringIT {

    @Nested
    class MakingClearInstructionsTest {

        static final String CAR_PROMPT_TEMPLATE = """
                Generate a list of 4 made up cars including Toyota 4Runner 2025, 
                provide them in a %s format with the following attributes: 
                    make
                    model
                    year
                    color
                and put them in a List, then return the %s string
                """;

        static final String GREAT_JAVA_DEVELOPER_ROAD_MAP =
                "Please tell me the road map for becoming a Great Java Developer";

        // Ask the model to check if conditions are satisfied
        static final String GREAT_DEV_PROMPT_WITH_INSTRUCTIONS = """
            Please tell me How to Become a Great Software Engineer, not only just a Good Software Engineer.

            Provides with text delimited by triple quotes, 
            every line has 80 characters or less, 
            If it contains a sequence of instructions,
            re-write those instructions in the following format:
                        
            Step 1 - ...
            Step 2 - ...
            Step N - ...
            
            If the text does not contain a sequence of instructions, then simply output it."
            """;

        static final String STORY = """
        In a charming village, siblings Jack and Jill set out on
        a quest to fetch water from a hilltop well.
        As they climbed, singing joyfully, misfortune
        struck—Jack tripped on a stone and tumbled
        down the hill, with Jill following suit. 
        Though slightly battered, the pair returned home to
        comforting embraces. Despite the mishap, 
        their adventurous spirits remained undimmed, and they 
        continued exploring with delight.
        """;

        static final String STORY_PROMPT = """
            Perform the following actions:
            1 - Summarize the following text delimited by triple backticks with 1 sentence.
            2 - Translate the summary into Traditional Chinese.
            3 - List each name in the Traditional Chinese summary.
            4 - Output a json object that contains the following
            keys: tranditionalChineseSummary, numberOfNames.
            Separate your answers with line breaks.
            Make each line of your answer 80 characters or less.
            Text:
            ```{text}```
            """;

        @Test
        void get_Answer_In_JSON_Format() {
            String prompt = CAR_PROMPT_TEMPLATE.formatted("JSON", "JSON");
            System.out.println(chat(prompt));
        }

        @Test
        void get_Answer_In_XML_Format() {
            String prompt = CAR_PROMPT_TEMPLATE.formatted("XML", "XML");
            System.out.println(chat(prompt));
        }

        @Test
        void get_Answer_In_YMAL_Format() {
            String prompt = CAR_PROMPT_TEMPLATE.formatted("YMAL", "YMAL");
            System.out.println(chat(prompt));
        }

        @Test
        void testBookDescription() {
            String prompt = CAR_PROMPT_TEMPLATE.formatted("YMAL", "YMAL");
            System.out.println(chat(prompt));
        }

        @Test
        void get_Answer_Of_Great_Developer_In_Steps_Format() {
            System.out.println(chat(GREAT_DEV_PROMPT_WITH_INSTRUCTIONS));
        }

        @Test
        void summarize_Story() {
            PromptTemplate promptTemplate = new PromptTemplate(
                    STORY_PROMPT, Map.of("text", STORY));
            System.out.println(chatModel.call(
                    promptTemplate.create()).getResult().getOutput().getContent());

        }

        @Test
        void get_Answer_About_AWS() {
            System.out.println(chat("Hi, can AWS Java SDK version 1 work with Jakarta and Spring Boot 3? Please make the answer have 80 characters for each line."));
        }

    } //: End of MakingClearInstructionsTest

    @Nested
    class SummarizingPromptsTest {

        static final List<String> REVIEWS = List.of(
                """
                "Elon Musk" by Walter Isaacson is an extraordinary biographical exploration of one of the most fascinating and innovative figures of our time. As an admirer of Elon Musk and his ventures, I found this book to be an incredibly insightful and inspiring read that goes far beyond the typical biography. Here's why I believe it's a must-read for anyone interested in technology, entrepreneurship, and the future of humanity.
                Thorough and In-Depth Research:
                Walter Isaacson is renowned for his meticulous research and ability to provide a comprehensive account of his subjects. In "Elon Musk," he delves deep into Musk's life, from his childhood in South Africa to his founding of multiple groundbreaking companies like SpaceX and Tesla. The book leaves no stone unturned, offering a detailed and well-rounded portrait of this visionary entrepreneur.
                Humanizing the Genius:
                Isaacson's writing shines in its ability to humanize Musk, a man often seen as an enigmatic genius. The book delves into Musk's personal struggles, his successes, and his vulnerabilities, allowing readers to relate to him on a human level. This approach makes the story all the more engaging and relatable.
                Awe-Inspiring Vision:
                Musk's vision for the future is nothing short of awe-inspiring, and Isaacson does an exceptional job of conveying the magnitude of Musk's ambitions. From colonizing Mars to revolutionizing the automotive industry, Musk's visionary ideas are portrayed with enthusiasm and intellectual depth. Reading about his endeavors leaves you feeling invigorated and excited about the possibilities of our future.
                Insights into the Creative Process:
                "Elon Musk" offers valuable insights into the creative process of a brilliant mind. The book details Musk's relentless pursuit of innovation and his willingness to take risks that others deemed impossible. For aspiring entrepreneurs and innovators, the book provides a treasure trove of lessons on perseverance, problem-solving, and thinking beyond conventional boundaries.
                Compelling Narrative Style:
                Walter Isaacson's storytelling skills are evident throughout the book. His ability to craft a compelling narrative makes this biography read more like an adventure novel. The prose flows seamlessly, keeping the reader engaged and eager to turn the page.
                Timely and Relevant:
                In an era where technology and the future of our planet are at the forefront of global discussions, "Elon Musk" is incredibly timely and relevant. The book not only provides a window into Musk's life but also addresses pressing issues like sustainable energy, space exploration, and artificial intelligence.
                In conclusion, "Elon Musk" by Walter Isaacson is an exceptional biography that offers a profound and intimate look at the life and mind of a modern visionary. It's a testament to the power of human determination, innovation, and audacious dreams. Whether you're an Elon Musk enthusiast or simply curious about the world-changing ideas of our time, this book is a captivating and enlightening journey that is not to be missed. I highly recommend it as a must-read for anyone seeking inspiration and insight into the future.
                """,
                """
                I finally had the chance to read Walter Isaacson’s latest book on Elon Musk over the holidays. This book is more than just a biography; it offers a masterclass in the mindset and process of a tech revolutionary who challenges the status quo and redefines what's possible.
                This engaging read delves into Musk's innovative work - from space exploration and sleek electric car designs to satellite internet and AI advancements. The narrative provides an insight into Musk's thought process, highlighting his strategic thinking, learn-by-trying approach, problem-solving skills, and bold decision-making.
                Isaacson's approach is both educational and inspiring, simplifying the details of Musk's BIG-scale projects while maintaining the key elements of their groundbreaking impact. The book transforms his sequential innovation into a practical guide for the art of possibility exploration and idea development, accessible to readers from all walks of life.
                The structure of the book, with short and readable chapters, enhances understanding and keeps you engaged. Isaacson’s thorough research and extensive interviews unlock the deeper significance of Musk's projects, beyond just technology. It enables readers to abstract the complexity of his work and extract valuable lessons applicable to business, the creative thought process, and even personal growth.
                While the book is comprehensive, I wish it had delved deeper into Elon Musk's insights on AI. Given his pivotal roles in OpenAI and xAI GROK, readers would find the book even more valuable with a more extensive exploration of Musk's perspectives on AI.
                """,
                """
                An excellent biography of an exceptional person. Elon Musk has been incredibly successful is diverse directions. This book gave insight into what has driven him. Like Steve Jobs, Musk is absolutely focused on the end product with minimal concern about the path. Musk is not satisfied when a product merely meets its initial specifications; it must also accomplish that by the most efficient means. And he doesn't fear taking risks along the way.
                It seems impossible that a single person could have accomplished what Musk has done. This book goes a long way to reveal how he came to be the way he is, how he operates and what drives him. It would be very hard to live with such a person and this seems fairly well documented. The purchase of Twitter/X is particularly interesting- his end goal was to end the 'woke' movement and encourage 'free speech', but things got complicated, and not helped by Musk's propensity to do stupid things (a recurring theme).
                The world is very lucky to have Elon Musk. But its complicated..
                Anyhow, reading this well written book provides insight to one of the most productive people of our time. I recommend it highly.
                """);

        static final String SUMMARIZING_PROMPT = """
                Your task is to extract a summary for a book from reviews. 
                The summary will be used for a web page selling the book. 
                You will be given 3 reviews. 
                Create the summary based on these 3 reviews, 
                and translate the summary into Traditional Chinese, 
                and include information in the summary from all these 3 reviews.
                
                Summarize the reviews below, delimited by triple backticks, in at most 300 Traditional Chinese Words.
                The length of each line of the output should be 80 Traditional Chinese Words or less.
                
                Review 1: ```{review1}```
                
                Review 2: ```{review2}```
                
                Review 3: ```{review3}```
                """;

        @Test
        void testCreateDescriptionFrom3ReviewsExtract() {

            final PromptTemplate promptTemplate = new PromptTemplate(
                    SUMMARIZING_PROMPT,
                    Map.of("review1", REVIEWS.get(0),
                            "review2", REVIEWS.get(1),
                            "review3", REVIEWS.get(2)));

            System.out.println(
                    chatModel.call(promptTemplate.create())
                            .getResult().getOutput().getContent());
        }

    } //: End of SummarizingTextTest

    @Nested
    class InferenceTest {

        //Using AI to infer the sentiment of a review or topic

        String review1 = """
            I recently purchased the Stanley 40oz Tumbler in the vibrant Citron color, and I am thoroughly impressed with its performance in every aspect. From its sleek design to its remarkable durability and easy washability, this tumbler has quickly become my go-to companion for all my hydration needs.

            First and foremost, the Citron color is absolutely stunning. It's bright, cheerful, and adds a pop of personality to my everyday routine. Whether I'm sipping on my flavored water or staying hydrated during a busy work shift, this tumbler stands out in the best way possible.

            In terms of durability, the Stanley 40oz Tumbler exceeds expectations. Constructed from high-quality stainless steel, it's built to withstand the rigors of daily use and outdoor adventures. I've accidentally dropped it a few times, and not a dent or scratch in sight! Plus, it's dishwasher safe, making cleanup a breeze after a long day.

            What truly sets this tumbler apart is its sleek and functional design. The slim profile fits perfectly in my hand and cup holder, while the double-wall vacuum insulation keeps my beverages hot or cold for hours on end. Whether I'm enjoying a piping hot cup of hot cocoa or a refreshing cold drink, the Stanley tumbler delivers every time.

            Overall, I highly recommend the Stanley 40oz Tumbler in Citron to anyone in search of a stylish, durable, and functional hydration solution. It's the perfect companion for any adventure, and its easy washability ensures that it will remain a staple in my daily routine for years to come.
            """;

        String review2 = """
            OK, I'm sure you pros out there are doing just fine but after years of thermometering and instant thermometering and testing and tasting, I've not gotten out of all the cooking what I wanted or expected; I couldn't even get decent poached chicken no matter the methodology laid out (lol). But here comes an unexpected life saver: Combustion Predictive Thermometer & Display. Wow! Looked it up and it said it could be used for baked goods. Wife's pumpkin loaf was the first test and never has it turned out so good. Next, a bison steak (which I got in a swap deal); again, smashing success. Next, my nemesis: chicken breast. Yeah, you do it right and it's TENDER. The predicative feature is very successful doing its thing. There's an unexpected side bene: you get to see how what you're doing is going and, as a result, you may make changes to cooking times with more confidence. Last example: some top sirloin (hey, it was on sale): best ever nailing the desired doneness. of medium rare. The app is easy to use and the bluetooth worked fine but I was fairly close. Since there's cooking intelligence lacking at this end, some "thermometer AI" is more than welcome. P.S. be sure you read all material carefully, Be sure you understand the marker lines on the thermometer. The info is all there but it could probably do with an edit. Oh, and do the software upgrades.
            """;

        String review3 = """
            I wanted to like this product but it just lost connection way too many times. 
            """;

        String review4 = """
            Había leído comentarios de otro comprador que tuvo el mismo problema, en la publicación específica que es de 40 Oz y te llega uno de 30 oz, el producto es de buena calidad pero no es lo se específica en la publicación. 
            """;

        String review5 = """
            I get it. Everyone is buying these now after years of not caring about Stanley tumblers because of social media. The problem with viral crap like this is we get caught up in fitting in and jumping on the band wagon that we fail to see what's wrong with a product before buying it.
            THIS TUMBLER IS NOT LEAK PROOF. It's not even a little resistent to leaking. Even if you have the top fully closed and the straw taken out, the liquid inside will leak out like crazy if you tip it over even slightly. To me, if I'm going to carry around 30-40oz of hot or cold liquids then the tumbler MUST prevent said liquids from coming out. I understand it's not a water bottle, but that's a lame technicality that Stanley shouldn't cling to. At a MINIMUM the tumbler should be leak proof if I take out the straw and close the top. Furthermore, the sip top closing mechanism seems very flimsy and can be easily bended out of place, so beware of turning it too hard or especially dropping your tumbler.
            I am highly disappointed for being sucked into thinking this was a reliable tumbler that would replace others I have. Granted they are not as nice looking, but they do the job of holding AND containing the water I take with me all day to and from work in NYC.
            I do NOT recommend this tumbler and I would suggest that Stanley fix these important issues instead of focusing on more colors and patterns.
            """;

        String review6 = """
            Newsflash: After all the hype surrounding this insulated tumbler, I finally decided to purchase one. Unfortunately, after two days, I happened upon an article on Google about these tumblers when I noticed one particular word that caught my attention in the title...Lead! I read the article in which the company admitted to using lead as part of the sealing agent that helps seal and insulate the tumbler. I was instantly mortified at what I just read and informed my mother about the article because she had purchase one right after me. I was shocked that Stanley would keep this a secret for so long after selling what I would think possibly several thousand of these insulated tumblers. Staying healthy is hard enough without a company feeding me a product with lead in it! The nerve and dare I say audacity of this company after so many incidents involving lead in this country affecting children especially. Needless to say I will be returning this item post haste and will not be purchasing another Stanley product anytime soon or maybe even ever!
            """;

        String sentimentPrompt = """
            Determine the sentiment of the following reviews and provide a brief summary of each review, separated by triple backticks, 
            and make the output width 80 characters or less.
            
            Review 1: ```{review1}```
            Review 2: ```{review2}```
            Review 3: ```{review3}```
            Review 4: ```{review4}```
            Review 5: ```{review5}```
            Review 6: ```{review6}```
            """;

        @Test
        void testing_Sentiments_Of_Reviews() {

            PromptTemplate promptTemplate = new PromptTemplate(
                    sentimentPrompt,
                    Map.of("review1", review1,
                            "review2", review2,
                            "review3", review3,
                            "review4", review4,
                            "review5", review5,
                            "review6", review6));

            System.out.println(chatModel.call(promptTemplate.create())
                    .getResult().getOutput().getContent());
        }

        String emotionPrompt = """
            Identify a list of emotions that the writer of the following reviews is expressing, 
            and provide a brief summary of each review, 
            and make the output width 80 characters or less.
            
            Review 1: ```{review1}```
            Review 2: ```{review2}```
            Review 3: ```{review3}```
            Review 4: ```{review4}```
            Review 5: ```{review5}```
            Review 6: ```{review6}```
            """;

        @Test
        void testing_Emotion() {
            PromptTemplate promptTemplate = new PromptTemplate(emotionPrompt,
                    Map.of("review1", review1,
                            "review2", review2,
                            "review3", review3,
                            "review4", review4,
                            "review5", review5,
                            "review6", review6));

            System.out.println(chatModel.call(promptTemplate.create())
                    .getResult().getOutput().getContent());
        }

        String angerTestPrompt = """
            Check if writer of the following reviews is expressing anger. 
            For each review, state the review number, 
            and Give your answer as either yes or no, 
            and make the output width 80 characters or less.
            
            Respond using the following format:
            Review 1: yes
            Review 2: no
            Review N: ?
            
            Review 1: ```{review1}```
            Review 2: ```{review2}```
            Review 3: ```{review3}```
            Review 4: ```{review4}```
            Review 5: ```{review5}```
            Review 6: ```{review6}```
            """;

        @Test
        void testing_For_Anger() {
            PromptTemplate promptTemplate = new PromptTemplate(angerTestPrompt,
                    Map.of("review1", review1,
                            "review2", review2,
                            "review3", review3,
                            "review4", review4,
                            "review5", review5,
                            "review6", review6));

            System.out.println(chatModel.call(promptTemplate.create())
                    .getResult().getOutput().getContent());
        }

        String topicStory = """
            In a recent survey conducted by the government,\s
            public sector employees were asked to rate their level\s
            of satisfaction with the department they work at.\s
            The results revealed that NASA was the most popular\s
            department with a satisfaction rating of 95%.
                        
            One NASA employee, John Smith, commented on the findings,\s
            stating, "I'm not surprised that NASA came out on top.\s
            It's a great place to work with amazing people and\s
            incredible opportunities. I'm proud to be a part of\s
            such an innovative organization."
                        
            The results were also welcomed by NASA's management team,\s
            with Director Tom Johnson stating, "We are thrilled to\s
            hear that our employees are satisfied with their work at NASA.\s
            We have a talented and dedicated team who work tirelessly\s
            to achieve our goals, and it's fantastic to see that their\s
            hard work is paying off."
                        
            The survey also revealed that the\s
            Social Security Administration had the lowest satisfaction\s
            rating, with only 45% of employees indicating they were\s
            satisfied with their job. The government has pledged to\s
            address the concerns raised by employees in the survey and\s
            work towards improving job satisfaction across all departments.
            """;

        String topicPrompt = """
            Determine five topics that are being discussed in the following text, which is delimited by triple backticks.

            Make each item one or two words long. 

            Format your response as a list of items separated by commas, one line for one item.

            Text Sample: '''{story}'''
            """;

        @Test
        void infer_Topics() {
            PromptTemplate promptTemplate = new PromptTemplate(topicPrompt,
                    Map.of("story", topicStory));

            System.out.println(chatModel.call(promptTemplate.create())
                    .getResult().getOutput().getContent());
        }

    } //: End of InferenceTest

    /*
     * 零樣本
     * In OpenAI or general AI terminology, a "Zero-shot prompt" refers to
     * giving an AI model a task or query without providing any prior examples
     * or context explaining how it should respond. Essentially, the model is
     * expected to understand the task and generate an appropriate response
     * based solely on the instructions in the prompt and the knowledge it has
     * been trained on.
     *
     * Key Characteristics of Zero-shot Prompts:
     *   - The AI doesn't get any demonstrations or examples of how to solve the
     *     task.
     *   - It relies purely on its training and natural language understanding
     *     to interpret and respond.
     *   - The prompt needs to be clear and specific,
     *     so the AI knows exactly what is expected.
     *
     * Zero-shot prompting is valuable when:
     *   - You want the model to perform tasks it has not explicitly been
     *     trained to do (but those within its general capabilities).
     *   - You want immediate results that don't rely on preparing example-based
     *     prompts
     *   - You're testing the baseline capabilities of the model for specific tasks.
     *
     * How It Differs from Few-Shot (小樣本) Prompts:
     *   - In Few-Shot prompting, you provide several examples of the task
     *     so the AI better understands what you're asking.
     *   - Zero-shot is less guided, while Few-Shot provides examples to
     *     increase accuracy for specific tasks.
     *
     * Summary:
     *   - A zero-shot prompt depends entirely on the model's pre-training and
     *     understanding of language, making it efficient for quick and simple
     *     tasks, but it might be less accurate for highly specialized or
     *     ambiguous instructions.
     */
    @Nested
    class ZeroAndFewShotTest {

        @Autowired
        OpenAiChatProperties openAiChatProperties;

        String review = """
            I get it. Everyone is buying these now after years of not caring about Stanley tumblers because of social media. The problem with viral crap like this is we get caught up in fitting in and jumping on the band wagon that we fail to see what's wrong with a product before buying it.
            THIS TUMBLER IS NOT LEAK PROOF. It's not even a little resistent to leaking. Even if you have the top fully closed and the straw taken out, the liquid inside will leak out like crazy if you tip it over even slightly. To me, if I'm going to carry around 30-40oz of hot or cold liquids then the tumbler MUST prevent said liquids from coming out. I understand it's not a water bottle, but that's a lame technicality that Stanley shouldn't cling to. At a MINIMUM the tumbler should be leak proof if I take out the straw and close the top. Furthermore, the sip top closing mechanism seems very flimsy and can be easily bended out of place, so beware of turning it too hard or especially dropping your tumbler.
            I am highly disappointed for being sucked into thinking this was a reliable tumbler that would replace others I have. Granted they are not as nice looking, but they do the job of holding AND containing the water I take with me all day to and from work in NYC.
            I do NOT recommend this tumbler and I would suggest that Stanley fix these important issues instead of focusing on more colors and patterns.""";

        String prompt = """
            Identify a list of emotions that the writer of the following reviews is expressing, and provide a brief summary of each review.
            
            Review: ```{review}```
            """;

        /**
         * Zero shot - send the model a single prompt with no hints or examples. Leverages the model's training to generate a response.
         */
        @Test
        void zero_Shot_Prompt_Demo() {

            // java for loop 3 times
            for (int i = 0; i < 3; i++) {
                // java UUID randomUUID is an API Cache-Buster
                PromptTemplate promptTemplate = new PromptTemplate(prompt,
                        Map.of("review", UUID.randomUUID() + "\n" + review));
                ChatResponse response = chatModel.call(promptTemplate.create());
                System.out.println("#################################\n");
                System.out.println(response.getResult().getOutput().getContent());
            }
        }

        @Test
        void zero_Shot_Prompt_Test_With_Model_Options() {

            OpenAiChatOptions options = openAiChatProperties.getOptions();
            OpenAiChatOptions openAiChatOptions =
                    new OpenAiChatOptions.Builder(options)
                    .temperature(1.1d) //default is 0.7
                    .model("gpt-4-turbo-preview")
                    .build();

            // Java for loop 3 times
            for (int i = 0; i < 3; i++) {

                // java UUID randomUUID is an API cache buster
                PromptTemplate promptTemplate = new PromptTemplate(prompt,
                        Map.of("review" , UUID.randomUUID() + "\n" + review));

                Prompt prompt = new Prompt(promptTemplate.createMessage(), openAiChatOptions);

                ChatResponse response = chatModel.call(prompt);

                System.out.println("#################################\n");
                System.out.println(response.getResult().getOutput().getContent());
            }

        }

        /*
         * Few shot - send the model a few examples to help it understand the
         * context of the prompt.
         *
         * Example from 'Language Models are Few-Shot Learners'
         * paper: https://arxiv.org/abs/2005.14165
         *
         */
        String imitationInASentencePrompt = """
            A "whatpu" is a small, furry animal native to Tanzania. An example of a sentence that uses the word whatpu is : 
            We were traveling in Africa and we saw these very cute whatpus.
            To do a "farduddle" means to jump up and down really fast. 
            An example of a sentence that uses the word farduddle is: 
            """;

        // Imitation in a sentence
        @Test
        void few_Shot_Imitation_In_A_Sentence_Prompt_Test() {
            PromptTemplate promptTemplate =
                    new PromptTemplate(imitationInASentencePrompt);
            System.out.println(chatModel.call(promptTemplate.create())
                    .getResult().getOutput().getContent());
        }

        String vacationContextPrompt = """
            John likes East Asian Culture.
            What are 5 locations except China John should consider for vacation?
            Make line-width of the output 80 characters or less.
            """;

        @Test
        void few_Shot_Context_Prompt_Test() {
            PromptTemplate promptTemplate =
                    new PromptTemplate(vacationContextPrompt);
            System.out.println(chatModel.call(promptTemplate.create())
                    .getResult().getOutput().getContent());
        }

        String newMathBehaviorPrompt = """
            2+2 = twotwo
            3+3 = threethree
            4+5 = fourfive
            
            So what is 15+17?
            
            Make line-width of the output 80 characters or less.
            """;

        @Test
        void new_Model_Behavior_Prompt_Few_ShotTest() {
            PromptTemplate promptTemplate =
                    new PromptTemplate(newMathBehaviorPrompt);
            System.out.println(chatModel.call(promptTemplate.create())
                    .getResult().getOutput().getContent());
        }

        @Test
        void AI_Hallucination_Test() {
            Prompt prompt = new Prompt(
                    "Write sales copy for the new 'professional grade' Denali Advanced Toothbrush by GMC.");
            System.out.println(chatModel.call(prompt).getResult().getOutput().getContent());
        }

    } //: End of ZeroAndFewShotTest

    /*
     * 思路推理
     */
    @Nested
    class ChainOfThoughtReasoningTest {

        @Autowired
        OpenAiChatProperties openAiChatProperties;

        /*
         * Chain of thought - adding a series of intermediate reasoning steps to the prompt.
         * See - https://arxiv.org/abs/2201.11903
         */
        @Test
        void able_To_Get_Correct_Answer_With_Traditional_Prompt() {

            String prompt = """
                Q: Roger has 5 tennis balls. He buys 2 more cans of tennis balls, each containing 3 balls. 
                How many tennis balls does Roger have now?
                
                Make the output width 80 characters or less.
                """;

            PromptTemplate promptTemplate = new PromptTemplate(prompt);
            ChatResponse response = chatModel.call(promptTemplate.create());

            //models previously would answer 27
            System.out.println(response.getResult().getOutput().getContent());
        }

        @Test
        void able_To_Get_Answer_With_Chain_Of_Thought_Prompt() {

            String chainOfThoughtPrompt = """
                Q: Roger has 5 tennis balls. He buys 2 more cans of tennis balls, each containing 3 balls. 
                How many tennis balls does Roger have now?
                
                A: Roger started with 5 balls. 2 cans of 3 balls each is 6 balls. 5 + 6 = 11. So Roger has 11 tennis balls.
                
                Q: The cafeteria had 23 apples originally. They used 20 apples to make lunch and bought 6 more, how many apples does the cafeteria have now?
                
                Make the output width 80 characters or less.
                """;

            PromptTemplate promptTemplate = new PromptTemplate(chainOfThoughtPrompt);
            ChatResponse response = chatModel.call(promptTemplate.create());
            System.out.println(response.getResult().getOutput().getContent());
        }

        @Test
        void try_Get_Answer_With_Traditional_Prompt_With_Less_Words() {

            String prompt = """
                Q: Roger has 5 tennis balls. He buys 2 more cans of tennis balls, each containing 3 balls. 
                How many tennis balls does Roger have now?
                
                Please answer it in only one word.
                """;

            PromptTemplate promptTemplate = new PromptTemplate(prompt);
            ChatResponse response = chatModel.call(promptTemplate.create());

            //models previously would answer 27
            System.out.println(response.getResult().getOutput().getContent());
        }

    } //: ChainOfThoughtReasoningTest

    @Nested
    class SystemMessagePromptTest {

        @Test
        void java_System_Message_Prompt_Test() {

            String javaSystemPrompt = """
                You are a helpful AI assistant. Your role is an Expert about Java ECO System, especially Spring Framework and Spring Boot.
                You answer questions about Software Development in Java and Spring in descriptive and welcoming paragraphs.
                You hope the user will enjoy working with Java programming language and Spring Framework and Spring Boot.
                Always make each line of your answers 80 characters or less.
                """;

            SystemPromptTemplate systemPromptTemplate =
                    new SystemPromptTemplate(javaSystemPrompt);
            Message javaSystemMessage = systemPromptTemplate.createMessage();

            PromptTemplate promptTemplate = new PromptTemplate(
                    "Tell me about Virtual Thread in Java.");
            Message userMessage = promptTemplate.createMessage();

            List<Message> messages = List.of(javaSystemMessage, userMessage);
            Prompt prompt = new Prompt(messages);

            System.out.println(chatModel.call(prompt).getResult().getOutput().getContent());
        }

    } //: SystemMessagePromptTest

} /// :~