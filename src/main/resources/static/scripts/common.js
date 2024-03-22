var swapper = {
	objects: {
		container: $('.wrapper'),
		calendar: $('.calendar-front'),
		days: $('.show-dates'),
		form: $('.calendar-back'),
		input: $('.calendar-back input'),
		buttons: $('.calendar-back button')
	},

	init: function() {
		instance = this;
		objects = this.objects;
		objects.form.hide()
		this.addListeners();
	},

	swap: function(currentSide, desiredSide) {
		objects.container.toggleClass('flip');
		currentSide.fadeOut(250,function() {
    		desiredSide.fadeIn(250);
		}); 
		/*currentSide.fadeOut(250);
		//currentSide.hide();
		desiredSide.fadeIn(250);
		//desiredSide.show();*/
	},

	addListeners: function() {
		objects.days.on('click', function(){
			instance.swap(objects.calendar, objects.form);
		});

		objects.buttons.on('click', function(){
			objects.form.hide();
			instance.swap(objects.form, objects.calendar);
		});
	}
}

swapper.init();