# Overview of Towing Heavy Loads

- We are going to build a AI Recommendation Expert to recommend a proper truck to tow a given boat.
- Numerous factors go into towing heavy loads
- Primary factor is the weight being towed
- The heavier the load, the stronger and more stable tow vehicle is need to pull it safely
- Generally vehicles can tow much more weight than they are rated for
- But - Can they stop it? Can the safely maintain control?
- Hence - The tail wagging the dog can happen

## Factors Which Increase Tow Capacity

- Engine Power - torque to pull the load
- Suspension Stiffness - to control the load at speed
- Braking Capacity - to stop the load
- Engine and Transmission Cooling - to prevent damage from overheating
- Trailer Brakes - to assist with stopping
- Weight Distribution Hitches - to alleviate weight on the rear of the tow vehicle

## Other Factors to Consider
- Boat Manufacturers Typically List the Dry Weight of the Boat
  - Dry Weight is the Boat with nothing in it. No fuel, oil, or water
  - A larger boat can hold 300 gallons of fuel or more. 300 gallons * 6.1 = 1,830 lbs
- The weight also does not include any gear or personal items on the boat
- Thus a boat listed at 9,500 lbs, can easily weigh 12,500 lbs when towed

## Coming Up In The Course

- We will be creating an AI Recommendation Expert to Recommend a proper Tow Vehicle for a given
boat
- Use a System Prompt to define the role the AI Expert is playing
- Use RAG to provide the AI Expert with information about specific boat models
- Yamaha publishes ‘Performance Bulletins’ on a large number of boats. These bulletins include the
dry weight and weight as tested. We will use the weight as tested for the towing weight of the boat.
- Use RAG to define the towing capabilities of a variety of trucks. We will provide the towing capacity as
determined by the manufacturer of the truck.
