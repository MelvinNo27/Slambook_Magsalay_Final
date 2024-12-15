package com.example.my_slambook1

import android.app.AlertDialog
import android.content.Intent
import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.util.Patterns
import android.widget.ArrayAdapter
import android.widget.*
import android.widget.EditText
import android.widget.LinearLayout
import java.time.LocalDate
import java.time.Period
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.my_slambook1.databinding.ActivityDisplayUserProfileBinding
import com.example.my_slambook1.databinding.ActivityMainBinding
import com.example.my_slambook1.databinding.ListBinding

class List : AppCompatActivity() {

    private lateinit var binding: ListBinding
    private lateinit var itemAdapter: ItemAdapter
    private var hobbiesData: Item? = null
    private var favoritesData: Item? = null
    private var questionsData: Item? = null
    private var selectedImageUri: Uri? = null
    private var selectedAvatar: Drawable? = null
    private val items = mutableListOf<Item>()  // List to hold items

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialize RecyclerView
        binding.recyclerView.layoutManager = LinearLayoutManager(this)

        itemAdapter = ItemAdapter(items) { selectedItem ->
            showItemDetails(selectedItem)
        }

        binding.recyclerView.adapter = itemAdapter

        binding.ListBackButton.setOnClickListener {
            startActivity(Intent(this, Form2::class.java))
            finish()
        }
        binding.addButton.setOnClickListener {
            // Open the dialog when the "Add" button is clicked
            openAddDialog()
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun openAddDialog() {
        // Create a dialog builder
        val dialogBuilder = AlertDialog.Builder(this)

        // Inflate the dialog layout using ViewBinding
        val binding = ActivityMainBinding.inflate(layoutInflater)

        binding.profilePicture.setOnClickListener {
            // Inflate the dialog layout
            val dialogView = layoutInflater.inflate(R.layout.dialog_avatar_selection, null)
            val avatarDialog = AlertDialog.Builder(this)
                .setView(dialogView)
                .setCancelable(true)
                .create()

            // Find the GridLayout in the dialog
            val avatarGrid = dialogView.findViewById<GridLayout>(R.id.avatarGrid)

            // Set onClickListener for each avatar
            for (i in 0 until avatarGrid.childCount) {
                val avatar = avatarGrid.getChildAt(i) as ImageView
                avatar.setOnClickListener {
                    // Update profile picture with the selected avatar
                    binding.profilePicture.setImageDrawable(avatar.drawable)
                    selectedImageUri = null // Reset URI as avatar is selected
                    avatarDialog.dismiss()
                }
            }

            avatarDialog.show()
        }
        binding.favoriteHobbiesIcon.setOnClickListener {
            dialogToAddHobbies()
        }
        binding.favoriteIcon.setOnClickListener {
            dialogToAddFavorites()
        }
        binding.confusedIcon.setOnClickListener {
            dialogToAddQuestions()
        }

        val nameEditText = binding.fullNameInput
        val nicknameEditText = binding.nicknameInput
        val addressEditText = binding.Address
        val emailEditText = binding.Email
        val monthSpinner = binding.monthSpinner
        val daySpinner = binding.daySpinner
        val yearSpinner = binding.yearSpinner

        // Populate the Spinners with values
        val months = resources.getStringArray(R.array.monthName)
        val days = resources.getStringArray(R.array.monthDay)
        val years = resources.getStringArray(R.array.Years)

        val monthAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, months)
        val dayAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, days)
        val yearAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, years)

        monthSpinner.adapter = monthAdapter
        daySpinner.adapter = dayAdapter
        yearSpinner.adapter = yearAdapter

        dialogBuilder.setView(binding.root)

        // Show the dialog
        val dialog = dialogBuilder.create()
        dialog.show()
        binding.backButtons.setOnClickListener {
            dialog.dismiss()
        }

        // Set the btnSave button's click listener to handle the logic when clicked
        val btnSave = binding.btnSave // Assuming btnSave is in the dialog's layout
        btnSave.setOnClickListener {
            val name = nameEditText.text.toString()
            val nickname = nicknameEditText.text.toString()
            val address = addressEditText.text.toString()
            val email = emailEditText.text.toString()

            val birthMonth = monthSpinner.selectedItemPosition.toString().toInt() // Spinner months start from 0
            val birthDay = daySpinner.selectedItem.toString().toInt()
            val birthYear = yearSpinner.selectedItem.toString().toInt()

            val age =
                calculateAge(birthYear, birthMonth, birthDay) // Call the age calculation function

            if (name.isNotEmpty() && nickname.isNotEmpty() && address.isNotEmpty() && email.isNotEmpty()) {
                val newItem = Item(
                    fullName = name,
                    nickname = nickname,
                    address = address,
                    email = email,
                    birthMonth = birthMonth.toString(),
                    birthDay = birthDay.toString(),
                    birthYear = birthYear.toString(),
                    favoriteHobbies = hobbiesData?.favoriteHobbies ?: "",
                    freeTimeActivities = hobbiesData?.freeTimeActivities ?: "",
                    indoorOutdoorPreference = hobbiesData?.indoorOutdoorPreference ?: "",
                    wantedHobby = hobbiesData?.wantedHobby ?: "",
                    favoriteMovie = favoritesData?.favoriteMovie ?: "",
                    favoriteColor = favoritesData?.favoriteColor ?: "",
                    favoriteSong = favoritesData?.favoriteSong ?: "",
                    favoriteCelebrity = favoritesData?.favoriteCelebrity ?: "",
                    favoriteSport = favoritesData?.favoriteSport ?: "",
                    favoriteFood = favoritesData?.favoriteFood ?: "",
                    favoritePerson = favoritesData?.favoritePerson ?: "",
                    favoritePlace = favoritesData?.favoritePlace ?: "",
                    selfDescription = questionsData?.selfDescription ?: "",
                    biggestFear = questionsData?.biggestFear ?: "",
                    happinessSource = questionsData?.happinessSource ?: "",
                    selfLove = questionsData?.selfLove ?: "",
                    inspirationSource = questionsData?.inspirationSource ?: "",
                    uniqueTrait = questionsData?.uniqueTrait ?: "",
                    craziestThing = questionsData?.craziestThing ?: "",
                    proudOf = questionsData?.proudOf ?: ""
                )
                itemAdapter.addItem(newItem) // Update the RecyclerView
                Toast.makeText(this, "Item Added: $name\nAge: $age years", Toast.LENGTH_SHORT)
                    .show()
                intent.putExtra("userProfile", newItem)
                dialog.dismiss() // Close the dialog after adding the item
            } else {
                Toast.makeText(this, "Please enter all fields", Toast.LENGTH_SHORT).show()
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun calculateAge(year: Int, month: Int, day: Int): Int {
        val today = LocalDate.now() // Get today's date
        val birthDate = LocalDate.of(year, month, day) // Create a LocalDate for the birth date
        return if (birthDate.isBefore(today) || birthDate.isEqual(today)) {
            Period.between(birthDate, today).years // Calculate the years difference
        } else {
            0 // If the birth date is in the future, return 0 or handle it as needed
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun showItemDetails(item: Item) {
        // Create a dialog to show the item details
        val dialogBuilder = AlertDialog.Builder(this)
        val dialog = dialogBuilder.create()

        // Inflate the dialog layout using ViewBinding
        val binding = ActivityDisplayUserProfileBinding.inflate(layoutInflater)
        val userProfile = intent.getParcelableExtra<Item>("userProfile")

        val dialogView = layoutInflater.inflate(R.layout.activity_display_user_profile, null)
        val avatarImageView = dialogView.findViewById<ImageView>(R.id.profilePictureDisplay)

        // Set the avatar image if available
        selectedAvatar?.let {
            avatarImageView.setImageDrawable(it)
        }

        userProfile?.let {

            binding.fullNameText.text = it.fullName
            binding.nicknameText.text = it.nickname
            binding.addressText.text = it.address
            binding.emailText.text = it.email
            binding.Age.text = it.age.toString()

            binding.monthSpinner.text = it.birthMonth
            binding.daySpinner.text = it.birthDay
            binding.yearSpinner.text = it.birthYear

        }
        binding.backButton.setOnClickListener {
            dialog.dismiss()
        }
        binding.HobbiesIconDisplay.setOnClickListener {
            displayHobbies(item)
        }
        binding.favoriteIconDisplay.setOnClickListener {
            displayFavorites(item)
        }
        binding.confusedIconDisplay.setOnClickListener {
            displayQuestions(item)
        }
        binding.Update.setOnClickListener {
            openUpdateDialog(item)
        }
        // Set the item details in the dialog
        binding.fullNameText.text = "${item.fullName}"
        binding.nicknameText.text = "${item.nickname}"
        binding.addressText.text = "${item.address}"
        binding.emailText.text = "${item.email}"
        binding.monthSpinner.text = "${item.birthMonth}"
        binding.daySpinner.text = "${item.birthDay}"
        binding.yearSpinner.text = "${item.birthYear}"
        binding.genderGroup.check(R.id.maleRadioButton)
        binding.genderGroup.check(R.id.femaleRadioButton)
        binding.genderGroup.check(R.id.otherRadioButton)
        binding.genderGroup.check(R.id.maleRadioButton)
        binding.genderGroup.check(R.id.femaleRadioButton)
        binding.genderGroup.check(R.id.otherRadioButton)
        binding.Status.check(R.id.singleRadioButton)
        binding.Status.check(R.id.marriedRadioButton)
        binding.Status.check(R.id.DivorcedRadioButton)
        binding.Age.text = "${
            calculateAge(
                item.birthYear!!.toInt(),
                item.birthMonth!!.toInt(),
                item.birthDay!!.toInt()
            )
        }"

        binding.maleRadioButton.isEnabled = false
        binding.femaleRadioButton.isEnabled = false
        binding.otherRadioButton.isEnabled = false
        binding.singleRadioButton.isEnabled = false
        binding.marriedRadioButton.isEnabled = false
        binding.DivorcedRadioButton.isEnabled = false

        dialogBuilder.setView(binding.root)
        dialog.show()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun openUpdateDialog(item: Item) {
        val dialogBuilder = AlertDialog.Builder(this)

        // Inflate the dialog layout using ViewBinding
        val binding = ActivityMainBinding.inflate(layoutInflater)

        // Set the profile picture to show current avatar and allow selection
        binding.profilePicture.setOnClickListener {
            showAvatarSelectionDialog(binding)
        }

        // Set up click listeners for hobbies, favorites, and questions
        binding.favoriteHobbiesIcon.setOnClickListener {
            dialogToAddHobbies()
        }
        binding.favoriteIcon.setOnClickListener {
            dialogToAddFavorites()
        }
        binding.confusedIcon.setOnClickListener {
            dialogToAddQuestions()
        }

        // Fill existing values
        binding.fullNameInput.setText(item.fullName)
        binding.nicknameInput.setText(item.nickname)
        binding.Address.setText(item.address)
        binding.Email.setText(item.email)

        // Set spinners with the existing birthdate values
        setupSpinners(binding, item)

        dialogBuilder.setView(binding.root)
        val updateDialog = dialogBuilder.create()
        updateDialog.show()

        // Save the updated profile
        binding.btnSave.setOnClickListener {
            val updatedName = binding.fullNameInput.text.toString()
            val updatedNickname = binding.nicknameInput.text.toString()
            val updatedAddress = binding.Address.text.toString()
            val updatedEmail = binding.Email.text.toString()
            val updatedMonth = binding.monthSpinner.selectedItemPosition
            val updatedDay = binding.daySpinner.selectedItem.toString().toInt()
            val updatedYear = binding.yearSpinner.selectedItem.toString().toInt()

            // Validate inputs
            if (isValidInput(updatedName, updatedNickname, updatedAddress, updatedEmail)) {
                val updatedAge = calculateAge(updatedYear, updatedMonth + 1, updatedDay)

                // Create a new Item object with the updated data
                val updatedItem = item.copy(
                    fullName = updatedName,
                    nickname = updatedNickname,
                    address = updatedAddress,
                    email = updatedEmail,
                    birthMonth = (updatedMonth + 1).toString(),
                    birthDay = updatedDay.toString(),
                    birthYear = updatedYear.toString(),
                    age = updatedAge
                )

                // Update the RecyclerView item
                itemAdapter.updateItem(updatedItem)

                // Notify the user and close the dialog
                Toast.makeText(this, "Profile updated!", Toast.LENGTH_SHORT).show()
                updateDialog.dismiss()
            } else {
                Toast.makeText(this, "Please fill all fields correctly", Toast.LENGTH_SHORT).show()
            }
        }

        // Set the back button to dismiss the dialog
        binding.backButtons.setOnClickListener {
            updateDialog.dismiss()
        }
    }

    // Reusable method to handle avatar selection
    private fun showAvatarSelectionDialog(binding: ActivityMainBinding) {
        val dialogView = layoutInflater.inflate(R.layout.dialog_avatar_selection, null)
        val avatarDialog = AlertDialog.Builder(this)
            .setView(dialogView)
            .setCancelable(true)
            .create()

        val avatarGrid = dialogView.findViewById<GridLayout>(R.id.avatarGrid)
        for (i in 0 until avatarGrid.childCount) {
            val avatar = avatarGrid.getChildAt(i) as ImageView
            avatar.setOnClickListener {
                binding.profilePicture.setImageDrawable(avatar.drawable)
                selectedImageUri = null // Reset URI as avatar is selected
                avatarDialog.dismiss()
            }
        }
        avatarDialog.show()
    }

    // Method to populate spinners with months, days, and years
    private fun setupSpinners(binding: ActivityMainBinding, item: Item) {
        val months = resources.getStringArray(R.array.monthName)
        val days = resources.getStringArray(R.array.monthDay)
        val years = resources.getStringArray(R.array.Years)

        val monthAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, months)
        val dayAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, days)
        val yearAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, years)

        binding.monthSpinner.adapter = monthAdapter
        binding.daySpinner.adapter = dayAdapter
        binding.yearSpinner.adapter = yearAdapter

        // Set the spinner selections to the current values of the Item
        val birthMonthPosition = item.birthMonth?.toInt() ?: 0
        val birthDay = item.birthDay?.toInt() ?: 1
        val birthYear = item.birthYear?.toInt() ?: 2000

        binding.monthSpinner.setSelection(birthMonthPosition) // Spinner month starts at 0
        binding.daySpinner.setSelection(birthDay - 1)  // Spinner day starts at 0
        binding.yearSpinner.setSelection(birthYear - 2000) // Adjust for year selection range (if years are 2000-2100)
    }

    // Input validation
    private fun isValidInput(name: String, nickname: String, address: String, email: String): Boolean {
        return name.isNotEmpty() && nickname.isNotEmpty() && address.isNotEmpty() && email.isNotEmpty() && isValidEmail(email)
    }

    // Email validation
    private fun isValidEmail(email: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }




    private fun displayQuestions(item: Item) {
        val questions = """
            Self Description:  ${item.selfDescription ?: "Not set"}
            Biggest Fear:  ${item.biggestFear ?: "Not set"}
            Source of Happiness:  ${item.happinessSource ?: "Not set"}
            Love About Yourself:  ${item.selfLove ?: "Not set"}
            Most Inspirational Person:  ${item.inspirationSource ?: "Not set"}
            Unique Trait:  ${item.uniqueTrait ?: "Not set"}
            Craziest Thing Done:  ${item.craziestThing ?: "Not set"}
            Most Proud Of:  ${item.proudOf ?: "Not set"}
        """.trimIndent()

        AlertDialog.Builder(this)
            .setTitle("Questions")
            .setMessage(questions)
            .setPositiveButton("Close") { dialog, _ -> dialog.dismiss() }
            .create()
            .show()
    }

    private fun displayFavorites(item: Item) {
        val favorites = """
        Favorite Movie:  ${item.favoriteMovie}
        Favorite Color:  ${item.favoriteColor}
        Favorite Song:  ${item.favoriteSong}
        Favorite Celebrity:  ${item.favoriteCelebrity}
        Favorite Sport:  ${item.favoriteSport}
        Favorite Food:  ${item.favoriteFood}
        Favorite Person:  ${item.favoritePerson}
        Favorite Place:  ${item.favoritePlace}
    """.trimIndent()

        AlertDialog.Builder(this)
            .setTitle("Favorites")
            .setMessage(favorites)
            .setPositiveButton("Close", null)
            .show()
    }

    private fun displayHobbies(item: Item) {
        val hobbies = """
            Favorite Hobbies:  ${item.favoriteHobbies ?: "Not set"}
            Free Time Activities:  ${item.freeTimeActivities ?: "Not set"}
            Indoor/Outdoor Preference:  ${item.indoorOutdoorPreference ?: "Not set"}
            Hobby to Try:  ${item.wantedHobby ?: "Not set"}
        """.trimIndent()

        AlertDialog.Builder(this)
            .setTitle("Hobbies")
            .setMessage(hobbies)
            .setPositiveButton("Close") { dialog, _ -> dialog.dismiss() }
            .create()
            .show()
    }

    private fun dialogToAddFavorites() {
        val movieInput = EditText(this).apply { hint = "Enter your favorite movie" }
        val colorInput = EditText(this).apply { hint = "Enter your favorite color" }
        val songInput = EditText(this).apply { hint = "Enter your favorite song" }
        val celebrityInput = EditText(this).apply { hint = "Enter your favorite celebrity" }
        val sportInput = EditText(this).apply { hint = "Enter your favorite sport" }
        val foodInput = EditText(this).apply { hint = "Enter your favorite food" }
        val personInput = EditText(this).apply { hint = "Enter your favorite person" }
        val placeInput = EditText(this).apply { hint = "Enter your favorite place" }

        val layout = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            setPadding(40, 40, 40, 40)
            addView(movieInput)
            addView(colorInput)
            addView(songInput)
            addView(celebrityInput)
            addView(sportInput)
            addView(foodInput)
            addView(personInput)
            addView(placeInput)
        }

        AlertDialog.Builder(this)
            .setTitle("Add Your Favorite Things")
            .setView(layout)
            .setPositiveButton("Save") { _, _ ->
                favoritesData = Item(
                    favoriteMovie = movieInput.text.toString(),
                    favoriteColor = colorInput.text.toString(),
                    favoriteSong = songInput.text.toString(),
                    favoriteCelebrity = celebrityInput.text.toString(),
                    favoriteSport = sportInput.text.toString(),
                    favoriteFood = foodInput.text.toString(),
                    favoritePerson = personInput.text.toString(),
                    favoritePlace = placeInput.text.toString()
                )
                Toast.makeText(this, "Favorites saved successfully!", Toast.LENGTH_SHORT).show()
            }
            .setNegativeButton("Cancel", null)
            .show()
    }

    private fun dialogToAddQuestions() {
        val selfDescriptionInput = EditText(this).apply { hint = "How would you describe yourself in three words?" }
        val biggestFearInput = EditText(this).apply { hint = "What’s your biggest fear?" }
        val happinessInput = EditText(this).apply { hint = "What makes you the happiest?" }
        val selfLoveInput = EditText(this).apply { hint = "What do you love most about yourself?" }
        val inspirationInput = EditText(this).apply { hint = "Who inspires you the most in life?" }
        val uniqueAboutYouInput = EditText(this).apply { hint = "What’s something unique about you that most people don’t know?" }
        val craziestThingInput = EditText(this).apply { hint = "What’s the craziest thing you’ve ever done?" }
        val proudOfInput = EditText(this).apply { hint = "What are you most proud of?" }

        // Set up the layout for the dialog
        val layout = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            setPadding(40, 40, 40, 40)  // Add some padding for better spacing
            addView(selfDescriptionInput)
            addView(biggestFearInput)
            addView(happinessInput)
            addView(selfLoveInput)
            addView(inspirationInput)
            addView(uniqueAboutYouInput)
            addView(craziestThingInput)
            addView(proudOfInput)
        }
            // Create the dialog builder
            AlertDialog.Builder(this)
                .setTitle("Answer These Questions")
                .setMessage("Please answer the following questions about yourself:")
                .setView(layout)
                .setPositiveButton("Save") { _, _ ->
                    questionsData = Item(
                        selfDescription = selfDescriptionInput.text.toString(),
                        biggestFear = biggestFearInput.text.toString(),
                        happinessSource = happinessInput.text.toString(),
                        selfLove = selfLoveInput.text.toString(),
                        inspirationSource = inspirationInput.text.toString(),
                        uniqueTrait = uniqueAboutYouInput.text.toString(),
                        craziestThing = craziestThingInput.text.toString(),
                        proudOf = proudOfInput.text.toString()
                    )
                    Toast.makeText(this, "saved successfully!", Toast.LENGTH_SHORT).show()
                }
                .setNegativeButton("Cancel", null)
                .show()
        }

    private fun dialogToAddHobbies() {
        val favoriteHobbiesInput = EditText(this).apply { hint = "What are your favorite hobbies?" }
        val freeTimeInput = EditText(this).apply { hint = "How do you usually spend your free time?" }
        val indoorOutdoorInput = EditText(this).apply { hint = "Do you enjoy indoor or outdoor activities more?" }
        val tryHobbyInput = EditText(this).apply { hint = "What’s one hobby you’ve always wanted to try?" }


        // Set up the layout for the dialog
        val layout = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            setPadding(40, 40, 40, 40)  // Add padding for spacing
            addView(favoriteHobbiesInput)
            addView(freeTimeInput)
            addView(indoorOutdoorInput)
            addView(tryHobbyInput)
        }

        // Create the dialog builder
       AlertDialog.Builder(this)
            .setTitle("Add Your Hobbies")
            .setMessage("Please answer the following questions about your hobbies:")
            .setView(layout)
            .setPositiveButton("Save") { dialog, _ ->
                hobbiesData = Item(
                    favoriteHobbies = favoriteHobbiesInput.text.toString(),
                    freeTimeActivities = freeTimeInput.text.toString(),
                    indoorOutdoorPreference = indoorOutdoorInput.text.toString(),
                    wantedHobby = tryHobbyInput.text.toString(),
                )
                Toast.makeText(this, "saved successfully!", Toast.LENGTH_SHORT).show()
            }
           .setNegativeButton("Cancel", null)
           .show()
    }
}

