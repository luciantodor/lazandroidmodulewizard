{Hint: save all files to location: \jni }
unit unit2;
  
{$mode delphi}

interface
  
uses
  Classes, SysUtils, And_jni, And_jni_Bridge, Laz_And_Controls,
  unit3, unit4, unit5, unit6, unit7, unit8, unit9, unit10,
  unit11, unit12, unit13, unit14;
  
type

  { TAndroidModule2 }

  TAndroidModule2 = class(jForm)
      jButton1: jButton;
      jButton10: jButton;
      jButton11: jButton;
      jButton12: jButton;
      jButton2: jButton;
      jButton3: jButton;
      jButton4: jButton;
      jButton5: jButton;
      jButton6: jButton;
      jButton7: jButton;
      jButton8: jButton;
      jButton9: jButton;
      jImageList1: jImageList;
      jImageView1: jImageView;
      jTextView1: jTextView;
      jTextView2: jTextView;
      procedure DataModuleActive(Sender: TObject);
      procedure DataModuleCloseQuery(Sender: TObject; var CanClose: boolean);
      procedure DataModuleCreate(Sender: TObject);
      procedure DataModuleJNIPrompt(Sender: TObject);
      procedure DataModuleRotate(Sender: TObject; rotate: integer;
        var rstRotate: integer);
      procedure jButton10Click(Sender: TObject);
      procedure jButton11Click(Sender: TObject);
      procedure jButton12Click(Sender: TObject);
      procedure jButton1Click(Sender: TObject);
      procedure jButton2Click(Sender: TObject);
      procedure jButton3Click(Sender: TObject);
      procedure jButton4Click(Sender: TObject);
      procedure jButton5Click(Sender: TObject);
      procedure jButton6Click(Sender: TObject);
      procedure jButton7Click(Sender: TObject);
      procedure jButton8Click(Sender: TObject);
      procedure jButton9Click(Sender: TObject);
    private
      {private declarations}
    public
      {public declarations}
  end;
  
var
  AndroidModule2: TAndroidModule2;

implementation

{$R *.lfm}
  
{ TAndroidModule2 }

procedure TAndroidModule2.DataModuleCreate(Sender: TObject);
begin  //this initialization code is need here to fix Laz4Andoid  *.lfm parse.... why parse fails?
  Self.ActivityMode:= actMain;
  Self.BackgroundColor:= colbrBlack;
  //mode delphi
  Self.OnJNIPrompt:= DataModuleJNIPrompt;
  Self.OnRotate:= DataModuleRotate;
  Self.OnCloseQuery:= DataModuleCloseQuery;
  Self.OnActive:= DataModuleActive;
end;

procedure TAndroidModule2.DataModuleCloseQuery(Sender: TObject;
  var CanClose: boolean);
begin
  CanClose := True;
end;

procedure TAndroidModule2.DataModuleActive(Sender: TObject);
begin
   //ShowMessage('form 2 Active');
end;

procedure TAndroidModule2.DataModuleJNIPrompt(Sender: TObject);
begin
   Self.Show;
end;

procedure TAndroidModule2.DataModuleRotate(Sender: TObject; rotate: integer; var rstRotate: integer);
begin
   Self.UpdateLayout;
end;

procedure TAndroidModule2.jButton1Click(Sender: TObject);  //DEMO: Base Controls
begin
  if AndroidModule3 = nil then
  begin
    gApp.CreateForm(TAndroidModule3, AndroidModule3);
    AndroidModule3.Init(gApp);
  end
  else AndroidModule3.Show; //actRecyclable
end;

procedure TAndroidModule2.jButton2Click(Sender: TObject); //Demo: Memo and ScrollView
begin
  if AndroidModule4 = nil then
  begin
    gApp.CreateForm(TAndroidModule4, AndroidModule4);
    AndroidModule4.Init(gApp);
  end
  else AndroidModule4.Show;   //actRecyclable
end;

procedure TAndroidModule2.jButton3Click(Sender: TObject); //Demo: jWebView1
begin
  if AndroidModule5 = nil then
  begin
    gApp.CreateForm(TAndroidModule5, AndroidModule5);
    AndroidModule5.Init(gApp);
  end
  else AndroidModule5.Show; //actRecyclable
end;

procedure TAndroidModule2.jButton4Click(Sender: TObject);  //Demo: List View
begin
  if AndroidModule6 = nil then
  begin
    gApp.CreateForm(TAndroidModule6, AndroidModule6);
    AndroidModule6.Init(gApp);
  end
  else AndroidModule6.Show;  //actRecyclable
end;

procedure TAndroidModule2.jButton5Click(Sender: TObject);
begin
  if AndroidModule7 = nil then  //DEMO: jImageView , jScrollView , jImageBtn1
  begin
    gApp.CreateForm(TAndroidModule7, AndroidModule7);
    AndroidModule7.Init(gApp);
  end
  else AndroidModule7.Show; //actRecyclable
end;

procedure TAndroidModule2.jButton6Click(Sender: TObject);
begin
  if AndroidModule8 = nil then    //DEMO: jView1, jCanvas, jBitmap1
  begin
    gApp.CreateForm(TAndroidModule8, AndroidModule8);
    AndroidModule8.Init(gApp);
  end
  else AndroidModule8.Show; //actRecyclable
end;

procedure TAndroidModule2.jButton7Click(Sender: TObject);
begin
  if AndroidModule9 = nil then    //DEMO: jBitmap1  - pixels access...
  begin
    gApp.CreateForm(TAndroidModule9, AndroidModule9);
    AndroidModule9.Init(gApp);
  end
  else AndroidModule9.Show;         //actRecyclable
end;

procedure TAndroidModule2.jButton8Click(Sender: TObject);  //gl_1 2D
begin
  if AndroidModule10 = nil then
  begin
    gApp.CreateForm(TAndroidModule10, AndroidModule10);
    //AndroidModule10:= TAndroidModule10.Create(Self);
    AndroidModule10.Init(gApp);
  end
  else AndroidModule10.Show;
end;

procedure TAndroidModule2.jButton9Click(Sender: TObject); //gl_2 2D
begin
  if AndroidModule11 = nil then
  begin
    gApp.CreateForm(TAndroidModule11, AndroidModule11);
    //AndroidModule11:= TAndroidModule11.Create(Self);
    AndroidModule11.Init(gApp);
  end else AndroidModule11.Show;
end;

procedure TAndroidModule2.jButton10Click(Sender: TObject);  //gl_1 3D
begin
  if AndroidModule12 = nil then
  begin
    gApp.CreateForm(TAndroidModule12, AndroidModule12);
    //AndroidModule12:= TAndroidModule12.Create(Self);
    AndroidModule12.Init(gApp);
  end else AndroidModule12.show;
end;

procedure TAndroidModule2.jButton11Click(Sender: TObject); //gl_2 3D
begin
  if AndroidModule13 = nil then
  begin
    gApp.CreateForm(TAndroidModule13, AndroidModule13);
    //AndroidModule13:= TAndroidModule13.Create(Self);
    AndroidModule13.Init(gApp);
  end else AndroidModule13.Show;
end;

procedure TAndroidModule2.jButton12Click(Sender: TObject);
begin
  if AndroidModule14 = nil then     //jAsyncTask1 / jHttpClient1
  begin
    gApp.CreateForm(TAndroidModule14, AndroidModule14);
    AndroidModule14.Init(gApp);
  end
  else AndroidModule14.Show;
end;

end.