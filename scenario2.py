from moviepy.editor import *
from moviepy.video.io.VideoFileClip import VideoFileClip
myvideoOne = VideoFileClip("atletico_celta.mp4").subclip(23,107)
myvideoTwo = VideoFileClip("atletico_celta.mp4").subclip(121,141)
final = CompositeVideoClip([myvideoOne,myvideoTwo])
final.write_videofile("chain.mp4", fps=myvideoOne.fps)