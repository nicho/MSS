/**
 * @license Copyright (c) 2003-2015, CKSource - Frederico Knabben. All rights reserved.
 * For licensing, see LICENSE.md or http://ckeditor.com/license
 */

CKEDITOR.editorConfig = function( config ) {
	// Define changes to default configuration here. For example:
	// config.language = 'fr';
	// config.uiColor = '#AADC6E';
	config.toolbar = 'Full'; 
	config.toolbar_Full = [
	                         ['Source','-','NewPage','Preview','-','Templates'],
	                         ['Cut','Copy','Paste','PasteText','PasteFromWord','-','Print','SpellChecker', 'Scayt'],
	                         ['Undo','Redo','-','Find','Replace','-','SelectAll','RemoveFormat'],
	                          '/',
	                         ['Bold','Italic','Underline','Strike','-','Subscript','Superscript'],
	                          ['NumberedList','BulletedList','-','Outdent','Indent','Blockquote'],
	                          ['JustifyLeft','JustifyCenter','JustifyRight','JustifyBlock'],
	                          ['Link','Unlink','Anchor'],
	                         ['Image','Table','HorizontalRule','SpecialChar','PageBreak'],
	                         '/',
	                           ['Styles','Format','Font','FontSize'],
	                          ['TextColor','BGColor']
	                       ];

	// Remove some buttons provided by the standard plugins, which are
	// not needed in the Standard(s) toolbar.
	config.removeButtons = 'Underline,Subscript,Superscript';

	// Set the most common block elements.
	config.format_tags = 'p;h1;h2;h3;pre';

	// Simplify the dialog windows.
	config.removeDialogTabs = 'image:advanced;link:advanced';
	config.filebrowserUploadUrl='uploadPictureOrFile.do';
};
