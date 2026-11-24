
package com.webdev.itquiz;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class QuizActivity extends AppCompatActivity {

    private TextView txtQuizCategory;
    private TextView txtQuestionNumber;
    private TextView txtQuestion;
    private TextView[] answerViews;
    private ProgressBar quizProgress;
    private Button btnNextQuestion;

    private String selectedCategory;
    private Question[] questions;

    private int currentQuestion = 0;
    private int selectedAnswer = -1;
    private int score = 0;

    private final int lightMauve = Color.parseColor("#E8D6E4");
    private final int white = Color.WHITE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        // Récupérer la catégorie choisie
        selectedCategory = getIntent().getStringExtra("category");

        if (selectedCategory == null || selectedCategory.isEmpty()) {
            selectedCategory = "Programmation";
        }

        // Charger les questions de la catégorie
        questions = getQuestionsForCategory(selectedCategory);

        // Récupérer les éléments de l'interface
        txtQuizCategory = findViewById(R.id.txtQuizCategory);
        txtQuestionNumber = findViewById(R.id.txtQuestionNumber);
        txtQuestion = findViewById(R.id.txtQuestion);

        quizProgress = findViewById(R.id.quizProgress);
        btnNextQuestion = findViewById(R.id.btnNextQuestion);

        answerViews = new TextView[]{
                findViewById(R.id.answerA),
                findViewById(R.id.answerB),
                findViewById(R.id.answerC),
                findViewById(R.id.answerD)
        };

        txtQuizCategory.setText(selectedCategory);
        quizProgress.setMax(questions.length);

        // Afficher la première question
        displayQuestion();

        // Gérer la sélection des réponses
        for (int i = 0; i < answerViews.length; i++) {

            final int answerIndex = i;

            answerViews[i].setOnClickListener(v -> {

                selectedAnswer = answerIndex;

                // Réinitialiser les réponses
                for (TextView answer : answerViews) {
                    answer.setBackgroundColor(white);
                }

                // Mettre en évidence la réponse sélectionnée
                answerViews[answerIndex].setBackgroundColor(lightMauve);
            });
        }

        // Bouton question suivante
        btnNextQuestion.setOnClickListener(v -> {

            if (selectedAnswer == -1) {
                Toast.makeText(this,
                        "Veuillez sélectionner une réponse !",
                        Toast.LENGTH_SHORT).show();
                return;
            }

            // Vérifier la réponse
            if (selectedAnswer == questions[currentQuestion].correctAnswer) {
                score++;
            }

            // Passer à la question suivante
            currentQuestion++;

            if (currentQuestion < questions.length) {
                displayQuestion();
            } else {
                finishQuiz();
            }
        });
    }

    // Retourner les questions selon la catégorie
    private Question[] getQuestionsForCategory(String category) {

        switch (category) {

            case "Programmation":
                return new Question[]{

                        new Question(
                                "Quel mot-clé permet de déclarer une classe en Java ?",
                                new String[]{"A. class", "B. create", "C. define", "D. object"},
                                0
                        ),

                        new Question(
                                "Quel type représente un nombre entier en Java ?",
                                new String[]{"A. String", "B. int", "C. boolean", "D. char"},
                                1
                        ),

                        new Question(
                                "Quelle instruction permet de tester une condition ?",
                                new String[]{"A. for", "B. while", "C. if", "D. import"},
                                2
                        ),

                        new Question(
                                "Quelle boucle exécute un bloc plusieurs fois selon une condition ?",
                                new String[]{"A. if", "B. switch", "C. return", "D. while"},
                                3
                        ),

                        new Question(
                                "Quelle valeur représente un booléen vrai en Java ?",
                                new String[]{"A. true", "B. yes", "C. 1", "D. valid"},
                                0
                        )
                };

            case "Bases de données":
                return new Question[]{

                        new Question(
                                "Que signifie SQL ?",
                                new String[]{"A. Simple Question Language",
                                        "B. Structured Query Language",
                                        "C. System Quick Language",
                                        "D. Standard Question Logic"},
                                1
                        ),

                        new Question(
                                "Quel est le rôle d'une clé primaire ?",
                                new String[]{"A. Décorer une table",
                                        "B. Supprimer une base",
                                        "C. Identifier chaque ligne de manière unique",
                                        "D. Trier les colonnes"},
                                2
                        ),

                        new Question(
                                "Quelle commande permet de récupérer des données ?",
                                new String[]{"A. SELECT", "B. DELETE", "C. DROP", "D. UPDATE"},
                                0
                        ),

                        new Question(
                                "Quelle commande ajoute une nouvelle ligne dans une table ?",
                                new String[]{"A. ALTER", "B. SELECT", "C. UPDATE", "D. INSERT"},
                                3
                        ),

                        new Question(
                                "À quoi sert une clé étrangère ?",
                                new String[]{"A. Chiffrer les données",
                                        "B. Relier deux tables",
                                        "C. Supprimer une colonne",
                                        "D. Créer un utilisateur"},
                                1
                        )
                };

            case "Réseaux":
                return new Question[]{

                        new Question(
                                "Que représente une adresse IP ?",
                                new String[]{"A. Un identifiant d'un appareil sur un réseau",
                                        "B. Un mot de passe",
                                        "C. Un langage de programmation",
                                        "D. Un type de fichier"},
                                0
                        ),

                        new Question(
                                "Quel est le rôle principal d'un routeur ?",
                                new String[]{"A. Imprimer des documents",
                                        "B. Stocker des fichiers",
                                        "C. Relier différents réseaux",
                                        "D. Créer des pages web"},
                                2
                        ),

                        new Question(
                                "Quel service traduit les noms de domaine en adresses IP ?",
                                new String[]{"A. FTP", "B. DNS", "C. HTML", "D. USB"},
                                1
                        ),

                        new Question(
                                "Quel protocole permet de consulter des pages web de manière sécurisée ?",
                                new String[]{"A. FTP", "B. SMTP", "C. HTTP", "D. HTTPS"},
                                3
                        ),

                        new Question(
                                "Que signifie LAN ?",
                                new String[]{"A. Local Area Network",
                                        "B. Large Access Number",
                                        "C. Logical Area Node",
                                        "D. Long Area Network"},
                                0
                        )
                };

            case "Cybersécurité":
                return new Question[]{

                        new Question(
                                "Qu'est-ce que le phishing ?",
                                new String[]{"A. Une sauvegarde",
                                        "B. Une tentative de vol d'informations par tromperie",
                                        "C. Un logiciel de traitement de texte",
                                        "D. Une mise à jour"},
                                1
                        ),

                        new Question(
                                "Quel est le rôle d'un antivirus ?",
                                new String[]{"A. Créer des comptes",
                                        "B. Accélérer le clavier",
                                        "C. Détecter et bloquer les logiciels malveillants",
                                        "D. Construire un réseau"},
                                2
                        ),

                        new Question(
                                "Quel mot de passe est le plus sécurisé ?",
                                new String[]{"A. 123456",
                                        "B. password",
                                        "C. amina2004",
                                        "D. T!g8#pL2@zQ"},
                                3
                        ),

                        new Question(
                                "Que signifie l'authentification à deux facteurs ?",
                                new String[]{"A. Utiliser deux méthodes de vérification",
                                        "B. Avoir deux comptes",
                                        "C. Changer de navigateur",
                                        "D. Installer deux antivirus"},
                                0
                        ),

                        new Question(
                                "Quel est le risque d'un lien suspect reçu par e-mail ?",
                                new String[]{"A. Améliorer la connexion",
                                        "B. Voler des informations personnelles",
                                        "C. Augmenter le stockage",
                                        "D. Accélérer l'ordinateur"},
                                1
                        )
                };

            case "Développement Web":
                return new Question[]{

                        new Question(
                                "Quel langage structure le contenu d'une page web ?",
                                new String[]{"A. SQL", "B. Python", "C. HTML", "D. Java"},
                                2
                        ),

                        new Question(
                                "Quel langage permet de styliser une page web ?",
                                new String[]{"A. CSS", "B. PHP", "C. SQL", "D. C"},
                                0
                        ),

                        new Question(
                                "Quel langage permet d'ajouter de l'interactivité à une page web ?",
                                new String[]{"A. HTML", "B. JavaScript", "C. CSS", "D. XML"},
                                1
                        ),

                        new Question(
                                "Que signifie HTTP ?",
                                new String[]{"A. High Text Transfer Program",
                                        "B. Home Tool Text Protocol",
                                        "C. Hyper Transfer Technology Process",
                                        "D. HyperText Transfer Protocol"},
                                3
                        ),

                        new Question(
                                "Quel langage est souvent utilisé pour développer le côté serveur d'un site web ?",
                                new String[]{"A. CSS", "B. HTML", "C. PHP", "D. XML"},
                                2
                        )
                };

            default:
                return getQuestionsForCategory("Programmation");
        }
    }

    // Afficher une question
    private void displayQuestion() {

        Question question = questions[currentQuestion];

        txtQuestionNumber.setText(
                "Question " + (currentQuestion + 1) + " / " + questions.length
        );

        txtQuestion.setText(question.question);

        quizProgress.setProgress(currentQuestion + 1);

        for (int i = 0; i < answerViews.length; i++) {
            answerViews[i].setText(question.answers[i]);
            answerViews[i].setBackgroundColor(white);
        }

        selectedAnswer = -1;

        if (currentQuestion == questions.length - 1) {
            btnNextQuestion.setText("Terminer le quiz");
        } else {
            btnNextQuestion.setText("Question suivante");
        }
    }

    // Terminer le quiz et ouvrir les résultats
    private void finishQuiz() {

        Intent intent = new Intent(
                QuizActivity.this,
                ResultsActivity.class
        );

        intent.putExtra("category", selectedCategory);
        intent.putExtra("score", score);
        intent.putExtra("total", questions.length);

        startActivity(intent);
        finish();
    }

    // Structure d'une question
    private static class Question {

        String question;
        String[] answers;
        int correctAnswer;

        Question(String question, String[] answers, int correctAnswer) {
            this.question = question;
            this.answers = answers;
            this.correctAnswer = correctAnswer;
        }
    }
}
