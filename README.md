# compassFinch
This Finch robot asks the user for an input in the form of a degree for a compass.
Once the user inputs the degree, it will move the bot to face that degree.
Once that degree is set, it will keep moving until an obstacle is detected.

What it should have done:
Pan the object, if distance is less than 10cm, its a wall and the bot should shutoff.
If the distance is greater, the Finch should readjust and try to get around the obsticle.

fallDetection method constraints:
- Can only approach a fall at a perpendicular angle with +/- 10d varience. Otherwise, the wheels will not work properly and the Finch will fall

check method constraints:
- The compass for Finch 2.0 robots is extremely inaccurate even with proper initialization inside the connection app. Variences will occur.
- +/- 1 motor speeds result in the Finch not working at all. Motor speeds for at least 1 L/R motor must be set past +/- 1,
- The Finch may skip over the angle multiple times due to compass inacuracy and motor speeds. These are variables that are hardware limitations

Feel free to add anything you want to the bot. All code is in Java. BlueBird was used to code this, and it's highly reccomended for visualization. 
