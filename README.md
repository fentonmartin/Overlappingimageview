# Overlappingimageview
Simple custom ImageView for android

The project Rational:

I made this project in order to achieve a simple way to be able overlapping mode for two images one on another.
This project helped me to build a gallery with multiple selection modes.
You can use Drawable or xml shape in front or behind the regular ImageView resource.
The Drawable will resize according to the ImageView size.

<img src="https://github.com/davidHarush/Overlappingimageview/blob/master/Screenshot.png" width="168" height="300" hspace="30" />
<img src="https://github.com/davidHarush/Overlappingimageview/blob/master/Screenshot_1.png" width="168" height="300"  hspace="30"/>
<img src="https://github.com/davidHarush/Overlappingimageview/blob/master/Screenshot_2.png" width="168" height="300"  hspace="30"/>

All you need to do is to copy the OverLappingImageView.java to your project and start using it, here some examples for the project use


exmple for drawble mask draw in front of the image
```
OverlappingImageView  mImg1 = ((OverlappingImageView) findViewById(R.id.imageView1));
mImg1.setMaskShape(R.drawable.mask_shape);
mImg1.setDrawOnFront(true);
```

exmple for drawble mask draw behind of the image
```
OverlappingImageView mImg2 = ((OverlappingImageView) findViewById(R.id.imageView2))
                .setMaskShape(android.R.drawable.picture_frame)
                .setDrawOnFront(false);
```




