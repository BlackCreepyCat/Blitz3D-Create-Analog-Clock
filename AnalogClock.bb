; ----------------------------------------
; Name : Create Analog Clock
; Date : (C)2025
; Site : https://github.com/BlackCreepyCat
; ----------------------------------------

Graphics 800,600,0,2 ; Définit la fenêtre graphique
SetBuffer BackBuffer() ; Utilise le double buffering

; Définition du Type Horloge
Type Horloge
    Field x#, y#        ; Position du centre
    Field rayon#        ; Rayon de l'horloge
End Type

; Création d'une instance d'horloge
Global monHorloge.Horloge = New Horloge
monHorloge\x# = 400
monHorloge\y# = 300
monHorloge\rayon# = 200

; Boucle principale
While Not KeyHit(1) ; Jusqu'à ce que ESC soit pressé
    Cls ; Efface l'écran
    DessinerHorloge(monHorloge) ; Dessine l'horloge
    Flip ; Affiche le rendu
Wend
End

Function DessinerHorloge(h.Horloge)
    ; Variables pour l'heure actuelle
    Local heures# = Float(Hour())
    Local minutes# = Float(Minute())
    Local secondes# = Float(Second())
    
    ; Dessiner le cercle de l'horloge
    Color 255,255,255 ; Blanc
    Oval h\x#-h\rayon#, h\y#-h\rayon#, h\rayon#*2, h\rayon#*2, 0 ; Cercle vide
    
    ; Dessiner les marques des heures (12 principales)
    For i = 0 To 11
        Local angle# = i * 30 - 90 ; 30° par heure, -90° pour commencer à 12h
        Local x1# = h\x# + Cos(angle#) * (h\rayon# * 0.9) ; Point intérieur
        Local y1# = h\y# + Sin(angle#) * (h\rayon# * 0.9)
        Local x2# = h\x# + Cos(angle#) * h\rayon# ; Point extérieur
        Local y2# = h\y# + Sin(angle#) * h\rayon#
        Line x1#, y1#, x2#, y2#
    Next
    
    ; Aiguille des heures (plus courte)
    Color 255,0,0 ; Rouge
    Local angleHeures# = (heures + minutes/60.0) * 30 - 90
    Local longueurHeures# = h\rayon# * 0.5
    Line h\x#, h\y#, h\x# + Cos(angleHeures#) * longueurHeures#, h\y# + Sin(angleHeures#) * longueurHeures#
    
    ; Aiguille des minutes (moyenne)
    Color 0,255,0 ; Vert
    Local angleMinutes# = (minutes + secondes/60.0) * 6 - 90
    Local longueurMinutes# = h\rayon# * 0.8
    Line h\x#, h\y#, h\x# + Cos(angleMinutes#) * longueurMinutes#, h\y# + Sin(angleMinutes#) * longueurMinutes#
    
    ; Aiguille des secondes (fine et longue)
    Color 0,0,255 ; Bleu
    Local angleSecondes# = secondes * 6 - 90
    Local longueurSecondes# = h\rayon# * 0.9
    Line h\x#, h\y#, h\x# + Cos(angleSecondes#) * longueurSecondes#, h\y# + Sin(angleSecondes#) * longueurSecondes#
    
    ; Centre de l'horloge
    Color 255,255,255
    Oval h\x#-5, h\y#-5, 10, 10, 1 ; Petit cercle plein
End Function

; Fonctions système Blitz3D pour obtenir l'heure
Function Hour%()
    Local time$ = CurrentTime$()
    Return Int(Mid$(time$, 1, 2)) ; Extrait les 2 premiers chiffres (heures)
End Function

Function Minute%()
    Local time$ = CurrentTime$()
    Return Int(Mid$(time$, 4, 2)) ; Extrait les chiffres 4-5 (minutes)
End Function

Function Second%()
    Local time$ = CurrentTime$()
    Return Int(Mid$(time$, 7, 2)) ; Extrait les chiffres 7-8 (secondes)
End Function
;~IDEal Editor Parameters:
;~C#Blitz3D