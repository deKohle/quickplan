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
				data: $(obj.currentTarget.form).serialize(),
				failure: function(data) {
					//TODO
                },
                success: function(data) {
					back = document.getElementsByClassName("calendar-back")[0];
					//back.getElementsByClassName("dates-of-day-inner")[0].innerHTML += data;
					const node = new DOMParser().parseFromString(data, "text/html").body.firstElementChild;
					back.getElementsByClassName("dates-of-day-inner")[0].appendChild(node);
					form = back.getElementsByClassName("new-date-form")[0];
					form.description.value = "";
					//because the html got changed
					//instance.addNewListeners(newThings);
                }
			});
		});
		$(obj).find(".delete-date").on('click', function(obj){
			//obj.currentTarget.form.submit();
			$.ajax({
				url: '/appointment',
				type: 'delete',
				actedUpon: obj,
				data: $(obj.currentTarget.form).serialize(),
				failure: function(data) {
					//TODO
                },
                success: function(data) {
					this.actedUpon.currentTarget.parentNode.remove()
                }
			});
		});
		$(obj).find(".update-date").on('click', function(obj){
			//obj.currentTarget.form.submit();
			$.ajax({
				url: '/appointment',
				type: 'put',
				actedUpon: obj,
				data: $(obj.currentTarget.form).serialize(),
				success: function(data) {
					//currently unnecessary, but maybe later the server changes some informations
					this.actedUpon.currentTarget.form.description.value=data.description
                },
                failure: function(data) {
					//TODO
                }
			});
		});
		//self.currentTarget == document.getElementsByTagName('main')[0];
		$("main").one("click", instance.returnFromBackground);
		$(".wrapper").on("click", function(evt) {
		    if (evt.stopPropagation) {evt.stopPropagation();}
		    else {evt.cancelBubble=true;}
		    return false;
		});
	},
	
	returnFromBackground: function() {
		objects.form.hide();
		instance.swap(objects.form, objects.calendar);
	},

	addListeners: function() {
		objects.days.on('click', function(obj){
			instance.fillForm(obj);
			instance.swap(objects.calendar, objects.form);
		});

		objects.buttons.on('click', function(){
			$("main").off("click");
			//document.getElementsByTagName('main')[0].removeEventListener("click",instance.returnFromBackground);
			instance.returnFromBackground();
		});
	}
}

swapper.init();