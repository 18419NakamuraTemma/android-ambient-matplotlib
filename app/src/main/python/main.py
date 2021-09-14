import ambient
import pandas as pd
import matplotlib.pyplot as plt
import io
import base64



def main():
    am = ambient.Ambient(*****, '****************','****************','******************') #ambient.Ambient(チャネルID,'ライトキー','リードキー','ユーザーID')
    d = am.read(n=2) #ambientからデータ取得（この場合は2件取得）
    df = pd.DataFrame(d) #pandasのデータフレームに変更
    df.head(3)
    df['created'] = pd.to_datetime(list(df['created']))
    df = df.set_index('created')
    fig,ax = plt.subplots() #figureにサブプロットを追加
    df.plot(subplots=True,ax=ax) #サブプロットに取得したデータをプロット
    fig.canvas.draw() 
    bio = io.BytesIO()
    fig.savefig(bio, format="png")
    img_str = base64.b64encode(bio.getvalue())
    return ""+str(img_str,'utf-8')
