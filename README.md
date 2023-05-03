# Myco Music Visualizer

Name: Lukas Vaiciulaitis

Student Number: C21522836

Class Group: TU858/2

# Description

fairly simple visualizer inspired by old graphic equalizers with additional effects
and a somewhat... mushroomy influence from a mushroomy? song choice.

a journey of a mushroom growing and sporulating, setting the scene for a mushroom take-over of the screen.
maybe this is the music of the mushrooms themselves! no im not insane, mushrooms sing songs!


## Video:

[![YouTube](https://i3.ytimg.com/vi/sRPUsP6Trbk/maxresdefault.jpg)](https://youtu.be/sRPUsP6Trbk)

# Instructions

when the visualizer is running the spacebar can be used to pause/play the song & visuals, and the mouse can be used to progress or rewind the song by clicking on
the x-axis, a visual progress bar is visible at the bottom

much of the scaling is relative and the display size can be set in the 'Myco' class
modify 'int numEllipses = 7;' 
and 'return parent.color(hue, 255, 10);' in 'getColor' method, third parameter for brightness
in Aura.java to adjust for a more performant or a smoother lighting effect

# How it works



# List of classes/assets

| Class/asset | Source |
|-----------|-----------|
| Aura.java | Self written |
| Bars.java | Self written |
| Mushroom.java | Self written |
| Myco.java | Self written |
| MycoVisual.java | Modified From skooter500's Visual.java |
| ProgressBar.java| Self written |
| Spores.java | Self written |
| VisualComponent.java | Self written |

 all contributions made by me and inspired by nature

- What I am most proud of

as far as coding is concerned i feared that the most difficult and daunting part would be creating the spore class
which constructs and draws spores appearing to sprinkle out randomly from beneath the mushroom cap. this is my proudest coding 
implementation in the assignment.
though this seemed like the most complex component to implement, i managed to get it working and looking as i imagined quicker than i expected.
this was due to both the experience i gained playing around with the previous labs that introduced similar techniques, 
and the brief yet concise and guided prep work i did to think in advance of what i need to do, how i will do it, and how 
i will implement it, rather than diving head first into an idea i hadn't deconstructed in my mind yet! it also took a lot of coffee and 
some hours of valuable potential dreaming time lost.

coding aside, i am most proud of the general synergy of the song and the visuals. my intention from the start was to tell some kind of a story
and something about this song creates a feeling or tone that i can't quite describe in words, however the eerieness, beauty, mystery, weirdness and maybe
even a feeling of immenent death and decay, i have already experienced when learning about, seeing, or thinking about the mysterious world of fungus. 
i thus decided to relate the two and tell the simple story of a mushroom growing and spreading its spores
though the visuals are minimal and cartoonish, frankly noobish and ugly, im happy i told the story and created a foundation that i could improve on in the future

man i love mushrooms.

- What I learned

where to begin?!
this is thus far my most complex scratch java project so far that implements the most components. despite that, i have learned important skills in creating 
a modular and adaptable program. i went from not understanding interfaces (or even caring!) to knowing why it exists, why we sometimes need it, and how it's beneficial
to modularizing a program that makes use of good object oriented programming practices.
i gained useful experience in working with shapes and visuals, learned how to use bezier curves, and went out of my way to learn more about them and why they're so cool!
much more little things inside of every component that add to my java and general programming experience.



# References
* https://github.com/skooter500/MusicVisuals


```Java
public void render()
{
    ui.noFill();
    ui.stroke(255);
    ui.rect(x, y, width, height);
    ui.textAlign(PApplet.CENTER, PApplet.CENTER);
    ui.text(text, x + width * 0.5f, y + height * 0.5f);
}
```
