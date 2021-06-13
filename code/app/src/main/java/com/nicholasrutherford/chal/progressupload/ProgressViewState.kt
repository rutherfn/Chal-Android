package com.nicholasrutherford.chal.progressupload

import com.nicholasrutherford.chal.ViewState

interface ProgressViewState : ViewState {
    var toolbarTitle: String
    var title: String
    var body: String
    var category: String
    var image: String
}