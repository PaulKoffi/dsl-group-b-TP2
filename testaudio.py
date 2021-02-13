from moviepy.editor import *
from moviepy.video.io.VideoFileClip import VideoFileClip

my_video_two = VideoFileClip("atletico_celta.mp4").subclip('02:21','02:41')
video_duration = int(my_video_two.duration)

print(video_duration)

