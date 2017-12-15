<div class="row addOption form-group">
	<button id="mcqMoreOptions" type="button" class="btn btn-default btn_lg"><i class="glyphicon glyphicon-plus"></i></button>
	<span>Add Option</span>
</div>
<div class="row mcqOptions form-group">
	<div class="col-sm-4 input-group  mcqOption form-group" id="mcqOption_1">
		<span class="input-group-addon">
	   		<input type="radio" name="correctAnswer" class="radioBtn" id="radioBtn_1" value="option_1">
		</span>
		<input type="text" class="form-control option" name="option_1" id="option_1" placeholder="Content for this choice" required>
	   	<span class="input-group-btn">
			<button class="btn btn-default removeOption" id="removeOption_1" type="button"><i class="glyphicon glyphicon-trash"></i></button>
	   	</span>
	</div>
</div>
<br/>
<div class="container-fluid text-left form-group">
	<span class="h2">Step 5:</span>
	<span class="h2">Answer Summary / Explanation</span>
</div>
<div class="container-fluid text-left desc-body form-group">
	<input type="text" form="mcqForm" id="activityExplanation" name="activityExplanation" path="activity.activityExplanation" placeholder="Enter some explanation or summary for the answer." ></input>
</div>