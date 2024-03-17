import whisper

# code utilisant whisper
model = whisper.load_model("small")
result = model.transcribe("ModelCreation/wav/RecordAudio_0.wav",language="fr");
print(result["text"])
