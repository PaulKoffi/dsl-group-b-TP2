from moviepy.editor import *
import numpy as np
from moviepy.video.tools.segmenting import findObjects

"""
    Vidéo coolEffects.mp4 qui correspond à vidéoOne
"""

####################################################################
# WE CREATE THE TEXT THAT IS GOING TO MOVE, WE CENTER IT.

screensize = (720,460)
txtClip = TextClip('Cool effect',color='white', font="Amiri-Bold",
                   kerning = 5, fontsize=100)
cvc = CompositeVideoClip( [txtClip.set_pos('center')],
                        size=screensize)

# THE NEXT FOUR FUNCTIONS DEFINE FOUR WAYS OF MOVING THE LETTERS


# helper function
rotMatrix = lambda a: np.array( [[np.cos(a),np.sin(a)], 
                                 [-np.sin(a),np.cos(a)]] )

def vortex(screenpos,i,nletters):
    d = lambda t : 1.0/(0.3+t**8) #damping
    a = i*np.pi/ nletters # angle of the movement
    v = rotMatrix(a).dot([-1,0])
    if i%2 : v[1] = -v[1]
    return lambda t: screenpos+400*d(t)*rotMatrix(0.5*d(t)*a).dot(v)
    
def cascade(screenpos,i,nletters):
    v = np.array([0,-1])
    d = lambda t : 1 if t<0 else abs(np.sinc(t)/(1+t**4))
    return lambda t: screenpos+v*400*d(t-0.15*i)

def arrive(screenpos,i,nletters):
    v = np.array([-1,0])
    d = lambda t : max(0, 3-3*t)
    return lambda t: screenpos-400*v*d(t-0.2*i)
    
def vortexout(screenpos,i,nletters):
    d = lambda t : max(0,t) #damping
    a = i*np.pi/ nletters # angle of the movement
    v = rotMatrix(a).dot([-1,0])
    if i%2 : v[1] = -v[1]
    return lambda t: screenpos+400*d(t-0.1*i)*rotMatrix(-0.2*d(t)*a).dot(v)

# WE USE THE PLUGIN findObjects TO LOCATE AND SEPARATE EACH LETTER

letters = findObjects(cvc) # a list of ImageClips

# WE ANIMATE THE LETTERS

def moveLetters(letters, funcpos):
    return [ letter.set_pos(funcpos(letter.screenpos,i,len(letters)))
              for i,letter in enumerate(letters)]

clips = [ CompositeVideoClip( moveLetters(letters,funcpos),
                              size = screensize).subclip(0,5)
          for funcpos in [vortex, cascade, arrive, vortexout] ]

# WE CONCATENATE EVERYTHING AND WRITE TO A FILE

final_clip = concatenate_videoclips(clips)
final_clip.write_videofile('coolTextEffects.mp4',fps=25)

###################################################################

#Vidéo 1
video_one = VideoFileClip("coolTextEffects.mp4")

#Vidéo 2
video_two = VideoFileClip('test.mp4')

#Texte 1
_txt_begin=TextClip("my title text goes here..", fontsize=70, color='white')
_txt_begin=_txt_begin.set_position('center').set_duration(10)
txt_begin = CompositeVideoClip( [_txt_begin], size=video_one.size)

#Texte 2
_txt_end=TextClip("this is the end..", fontsize=70, color='white')
_txt_end=_txt_end.set_position('center').set_duration(10)
#why do we need to do this?  I don't know, but it works..
txt_end = CompositeVideoClip( [_txt_end], size=video_one.size)

#Création de l'audio
audio_clip = AudioFileClip("songTest.mp4")


"""
Assemblage donc ici on met les vidéos dans l'ordre dans lequel on veut les voir
s'afficher donc on a 1 - le texte du début
                     2 - la vidéo 1
                     3 - la vidéo 2
                     4 - le texte de fin 
"""
#video is a VideoFileClip I've already read in..  # video=VideoFileClip("myvideo.mp4")
# ajout de l'audio que l'on veut sur toute la vidéo
final = concatenate_videoclips([txt_begin, video_one,video_two, txt_end],method='compose').set_audio(audio_clip)
# final.set_audio(audio_clip)
final.write_videofile("videoscenario1.mp4", fps=30)