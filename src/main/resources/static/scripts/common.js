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
	},
	
	fillForm: function(obj) {
		dates = obj.currentTarget.parentNode.getElementsByClassName('dates-of-day')[0];
		newThings = dates.cloneNode(true);
		addTo = objects.form.get(0).getElementsByClassName('fillable')[0];
		try {addTo.removeChild(addTo.lastChild);}catch(e){}
		newThings.hidden = false;
		instance.addNewListeners(newThings);
		addTo.appendChild(newThings);
	},
	
	addNewListeners: function(obj) {
		$(obj).find(".add-date").on('click', function(obj){
			//obj.currentTarget.form.submit();
			$.ajax({
				url: '/appointment',
				type: 'post',
				data: $(obj.currentTarget.form).form.serialize(),
				failure: function(data) {
					//TODO
                }
			});
		});
		$(obj).find(".update-date").on('click', function(obj){
			//obj.currentTarget.form.submit();
			$.ajax({
				url: '/appointment',
				type: 'put',
				data: $(obj.currentTarget.form).serialize(),
				success: function(data) {
					//TODO
                },
                failure: function(data) {
					//TODO
                }
			});
		});
	},

	addListeners: function() {
		objects.days.on('click', function(obj){
			instance.fillForm(obj);
			instance.swap(objects.calendar, objects.form);
		});

		objects.buttons.on('click', function(){
			objects.form.hide();
			instance.swap(objects.form, objects.calendar);
		});
	}
}

swapper.init();