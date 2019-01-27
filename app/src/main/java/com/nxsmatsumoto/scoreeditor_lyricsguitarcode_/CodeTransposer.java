package com.nxsmatsumoto.scoreeditor_lyricsguitarcode_;

public class CodeTransposer {

    final String[] CODE_M = {
            "C","C#","Db","D","D#","Eb","E","F","F#","Gb","G","G#","Ab","A","A#","Bb","B"};
    final String[] CODE_7 = {
            "C7","C#7","Db7","D7","D#7","Eb7","E7","F7","F#7","Gb7","G7","G#7","Ab7","A7","A#7","Bb7","B7"};
    final String[] CODE_m = {
            "Cm","C#m","Dbm","Dm","D#m","Ebm","Em","Fm","F#m","Gbm","Gm","G#m","Abm","Am","A#m","Bbm","Bm"};
    final String[] CODE_m7 = {
            "Cm7","C#m7","Dbm7","Dm7","D#m7","Ebm7","Em7","Fm7","F#m7","Gbm7","Gm7","G#m7","Abm7","Am7","A#m7","Bbm7","Bm7"};
    final String[] CODE_M7 = {
            "CM7","C#M7","DbM7","DM7","D#M7","EbM7","EM7","FM7","F#M7","GbM7","GM7","G#M7","AbM7","AM7","A#M7","BbM7","BM7"};
    final String[] CODE_mM7 = {
            "CmM7","C#mM7","DbmM7","DmM7","D#mM7","EbmM7","EmM7","FmM7","F#mM7","GbmM7","GmM7","G#mM7","AbmM7","AmM7","A#mM7","BbmM7","BmM7"};
    final String[] CODE_sus4 = {
            "Csus4","C#sus4","Dbsus4","Dsus4","D#sus4","Ebsus4","Esus4","Fsus4","F#sus4","Gbsus4","Gsus4","G#sus4","Absus4","Asus4","A#sus4","Bbsus4","Bsus4"};
    final String[] CODE_7sus4 = {
            "C7sus4","C#7sus4","Db7sus4","D7sus4","D#7sus4","Eb7sus4","E7sus4","F7sus4","F#7sus4","Gb7sus4","G7sus4","G#7sus4","Ab7sus4","A7sus4","A#7sus4","Bb7sus4","B7sus4"};
    final String[] CODE_dim = {
            "Cdim","C#dim","Dbdim","Ddim","D#dim","Ebdim","Edim","Fdim","F#dim","Gbdim","Gdim","G#dim","Abdim","Adim","A#dim","Bbdim","Bdim"};
    final String[] CODE_m7_5 = {
            "Cm7-5","C#m7-5","Dbm7-5","Dm7-5","D#m7-5","Ebm7-5","Em7-5","Fm7-5","F#m7-5","Gbm7-5","Gm7-5","G#m7-5","Abm7-5","Am7-5","A#m7-5","Bbm7-5","Bm7-5"};
    final String[] CODE_aug = {
            "Caug","C#aug","Dbaug","Daug","D#aug","Ebaug","Eaug","Faug","F#aug","Gbaug","Gaug","G#aug","Abaug","Aaug","A#aug","Bbaug","Baug"};
    final String[] CODE_add9 = {
            "Cadd9","C#add9","Dbadd9","Dadd9","D#add9","Ebadd9","Eadd9","Fadd9","F#add9","Gbadd9","Gadd9","G#add9","Abadd9","Aadd9","A#add9","Bbadd9","Badd9"};
    final String[] CODE_6 = {
            "C6","C#6","Db6","D6","D#6","Eb6","E6","F6","F#6","Gb6","G6","G#6","Ab6","A6","A#6","Bb6","B6"};
    final String[] CODE_m6 = {
            "Cm6","C#m6","Dbm6","Dm6","D#m6","Ebm6","Em6","Fm6","F#m6","Gbm6","Gm6","G#m6","Abm6","Am6","A#m6","Bbm6","Bm6"};


    public String toneChange(String lyrics, int value) {
        String[] words = lyrics.split(" ",0);
        String wkLyrics = "";
        int matchflag = 0;
        for(int i = 0; i < words.length; i++) {
            matchflag = 0;

            for(String code:CODE_M){
                if(words[i].equals(code)){
                    wkLyrics += keyChange(code,value) + " ";
                    matchflag = 1;
                }
            }

            for(String code:CODE_7){
                if(words[i].equals(code)){
                    String triad = "7";
                    wkLyrics += keyChange(code.replace(triad, ""),value) + triad + " ";
                    matchflag = 1;
                }
            }

            for(String code:CODE_m){
                if(words[i].equals(code)){
                    String triad = "m";
                    wkLyrics += keyChange(code.replace(triad, ""),value) + triad + " ";
                    matchflag = 1;
                }
            }

            for(String code:CODE_m7){
                if(words[i].equals(code)){
                    String triad = "m7";
                    wkLyrics += keyChange(code.replace(triad, ""),value) + triad + " ";
                    matchflag = 1;
                }
            }

            for(String code:CODE_M7){
                if(words[i].equals(code)){
                    String triad = "M7";
                    wkLyrics += keyChange(code.replace(triad, ""),value) + triad + " ";
                    matchflag = 1;
                }
            }

            for(String code:CODE_mM7){
                if(words[i].equals(code)){
                    String triad = "mM7";
                    wkLyrics += keyChange(code.replace(triad, ""),value) + triad + " ";
                    matchflag = 1;
                }
            }

            for(String code:CODE_sus4){
                if(words[i].equals(code)){
                    String triad = "sus4";
                    wkLyrics += keyChange(code.replace(triad, ""),value) + triad + " ";
                    matchflag = 1;
                }
            }

            for(String code:CODE_7sus4){
                if(words[i].equals(code)){
                    String triad = "7sus4";
                    wkLyrics += keyChange(code.replace(triad, ""),value) + triad + " ";
                    matchflag = 1;
                }
            }

            for(String code:CODE_dim){
                if(words[i].equals(code)){
                    String triad = "dim";
                    wkLyrics += keyChange(code.replace(triad, ""),value) + triad + " ";
                    matchflag = 1;
                }
            }

            for(String code:CODE_m7_5){
                if(words[i].equals(code)){
                    String triad = "m7-5";
                    wkLyrics += keyChange(code.replace(triad, ""),value) + triad + " ";
                    matchflag = 1;
                }
            }

            for(String code:CODE_aug){
                if(words[i].equals(code)){
                    String triad = "aug";
                    wkLyrics += keyChange(code.replace(triad, ""),value) + triad + " ";
                    matchflag = 1;
                }
            }

            for(String code:CODE_add9){
                if(words[i].equals(code)){
                    String triad = "add9";
                    wkLyrics += keyChange(code.replace(triad, ""),value) + triad + " ";
                    matchflag = 1;
                }
            }

            for(String code:CODE_6){
                if(words[i].equals(code)){
                    String triad = "6";
                    wkLyrics += keyChange(code.replace(triad, ""),value) + triad + " ";
                    matchflag = 1;
                }
            }

            for(String code:CODE_m6){
                if(words[i].equals(code)){
                    String triad = "m6";
                    wkLyrics += keyChange(code.replace(triad, ""),value) + triad + " ";
                    matchflag = 1;
                }
            }

            if(matchflag == 0) wkLyrics += words[i]+ " ";
        }

        return wkLyrics;
    }

    private String keyChange(String code, int value) {

        if (value == 1){
            switch (code) {
                case "C":
                    return "C#";
                case "C#":
                    return "D";
                case "Db":
                    return "D";
                case "D":
                    return "D#";
                case "D#":
                    return "E";
                case "Eb":
                    return "E";
                case "E":
                    return "F";
                case "F":
                    return "F#";
                case "F#":
                    return "G";
                case "Gb":
                    return "G";
                case "G":
                    return "G#";
                case "G#":
                    return "A";
                case "Ab":
                    return "A";
                case "A":
                    return "A#";
                case "A#":
                    return "B";
                case "Bb":
                    return "B";
                case "B":
                    return "C";
                default:
                    break;
            }
        } else {
            switch (code) {
                case "C":
                    return "B";
                case "C#":
                    return "C";
                case "Db":
                    return "C";
                case "D":
                    return "C#";
                case "D#":
                    return "D";
                case "Eb":
                    return "D";
                case "E":
                    return "Eb";
                case "F":
                    return "E";
                case "F#":
                    return "F";
                case "Gb":
                    return "F";
                case "G":
                    return "F#";
                case "G#":
                    return "G";
                case "Ab":
                    return "G";
                case "A":
                    return "G#";
                case "A#":
                    return "A";
                case "Bb":
                    return "A";
                case "B":
                    return "Bb";
                default:
                    break;

            }
        }
        return code;
    }
}
