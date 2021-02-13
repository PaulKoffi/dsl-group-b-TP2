from moviepy.editor import *
# from moviepy.video.fx import volumex
from moviepy.video.io.VideoFileClip import VideoFileClip

audio_clip = AudioFileClip("songTest.mp4").subclip('00:30','1:00')
# audio_clip.volumex(0.2)
my_video_two = VideoFileClip("atletico_celta.mp4").subclip('02:21','02:41')
audio_clip_2 = my_video_two.audio


compo = CompositeAudioClip([audio_clip_2.volumex(1.2),
                            audio_clip.set_start(5), # start at t=5s]
                            ])

video2 = my_video_two.set_audio(compo)
# my_video_two.set_audio(audio_clip)
video2.write_videofile("testAudio.mp4", fps=30)

