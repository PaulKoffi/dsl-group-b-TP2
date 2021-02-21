from moviepy.editor import *
# from moviepy.video.tools.credits import credits1

# # Load the mountains clip, cut it, slow it down, make it look darker
clip = (VideoFileClip('atletico_celta.mp4', audio=False)
           .subclip('00:23','00:40')
           .speedx(0.4)
           .fx( vfx.colorx, 0.7))

# # Save the first frame to later make a mask with GIMP (only once)
# # ~ clip.save_frame('mountainMask2.png')


# # Load the mountain mask made with GIMP
# # mountainmask = ImageClip('mountainMask2.png', ismask=True)
mountainmask = ImageClip('image_mountain.jpg').resize(0.2).set_duration(20)
# mountainmask = mountainmask.resize(1920,1080)

# # Generate the credits from a text file
# credits = credits1('credits.txt',3*clip.w/4).set_duration(10)
# # credits.set_duration(5)
# scrolling_credits = credits.set_pos(lambda t:('center',-10*(t+5)))
# # scrolling_credits.set_duration(10)
# # Make the credits scroll. Here, 10 pixels per second
# clip_scrolling = CompositeVideoClip([scrolling_credits])

# final = concatenate_videoclips([clip, clip_scrolling],method='compose')
                            
# final.subclip(4,60).write_videofile("match.mp4", fps=25)

w = 720
h = w*9/16 # 16/9 screen
moviesize = w,h



# THE RAW TEXT
txt = "\n".join([
"A long time ago, in a faraway galaxy,",
"there lived a prince and a princess",
"who had never seen the stars, for they",
"lived deep underground.",
"",
"Many years before, the prince's",
"grandfather had ventured out to the",
"surface and had been burnt to ashes by",
"solar winds.",
"",
"One day, as the princess was coding",
"and the prince was shopping online, a",
"meteor landed just a few megameters",
"from the couple's flat."
])


# Add blanks
txt = 10*"\n" +txt + 10*"\n"


# CREATE THE TEXT IMAGE


clip_txt = TextClip(txt,color='white', align='West',fontsize=25,
                    font='Xolonium-Bold', method='label')
clip_txt = clip_txt.set_position(lambda t: ('center',(-20 *(t + 5)))).set_duration(20)

# SCROLL THE TEXT IMAGE BY CROPPING A MOVING AREA

# txt_speed = 27
# fl = lambda gf,t : gf(t)[int(txt_speed*t):int(txt_speed*t)+h:]
# moving_txt= clip_txt.fl(fl, apply_to=['mask'])


# ADD A VANISHING EFFECT ON THE TEXT WITH A GRADIENT MASK

# grad = color_gradient(moving_txt.size,p1=(0,2*h/3),
#                 p2=(0,h/4),col1=0.0,col2=1.0)
# gradmask = ImageClip(grad,ismask=True)
# fl = lambda pic : np.minimum(pic,gradmask.img)
# moving_txt.mask = moving_txt.mask.fl_image(fl)


# WARP THE TEXT INTO A TRAPEZOID (PERSPECTIVE EFFECT)

# def trapzWarp(pic,cx,cy,ismask=False):
#     """ Complicated function (will be latex packaged as a fx) """
#     Y,X = pic.shape[:2]
#     src = np.array([[0,0],[X,0],[X,Y],[0,Y]])
#     dst = np.array([[cx*X,cy*Y],[(1-cx)*X,cy*Y],[X,Y],[0,Y]])
#     tform = tf.ProjectiveTransform()
#     tform.estimate(src,dst)
#     im = tf.warp(pic, tform.inverse, output_shape=(Y,X))
#     return im if ismask else (im*255).astype('uint8')

# fl_im = lambda pic : trapzWarp(pic,0.2,0.3)
# fl_mask = lambda pic : trapzWarp(pic,0.2,0.3, ismask=True)
# warped_txt= moving_txt.fl_image(fl_im)
# warped_txt.mask = warped_txt.mask.fl_image(fl_mask)


# BACKGROUND IMAGE, DARKENED AT 60%

# stars = ImageClip('../../videos/stars.jpg')
# stars_darkened = stars.fl_image(lambda pic: (0.6*pic).astype('int16'))


# COMPOSE THE MOVIE

# final = CompositeVideoClip([
#          warped_txt.set_pos(('center','bottom'))],
#          size = moviesize)
imgtxt = CompositeVideoClip([mountainmask, clip_txt.crossfadein(2)])

final = concatenate_videoclips([clip,imgtxt],method="compose")
# WRITE TO A FILE

final.write_videofile("match.mp4", fps=25)