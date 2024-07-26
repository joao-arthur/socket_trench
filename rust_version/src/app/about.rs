use relm4::prelude::*;

use crate::constants;

pub struct AboutModel;
pub struct AboutInit;

#[derive(Debug)]
pub enum AboutInput {}

#[derive(Debug)]
pub enum AboutOutput {}

#[relm4::component(pub)]
impl SimpleComponent for AboutModel {
    type Init = AboutInit;
    type Input = AboutInput;
    type Output = AboutOutput;

    view! {
        adw::AboutWindow {
            set_application_icon: constants::APP_ID,
            set_application_name: constants::APP_NAME,
            set_developer_name: constants::APP_DEVELOPER,
            set_version: constants::APP_VERSION,
            set_website: constants::APP_URL,
            set_issue_url: constants::APP_ISSUE_URL,
            set_developers: &["João Arthur <joao.lothamer@gmail.com>"],
            set_copyright: "© 2024 João Arthur",
            set_license_type: gtk::License::Agpl30Only
        }
    }

    fn init(
        _init: Self::Init,
        root: &Self::Root,
        _sender: ComponentSender<Self>,
    ) -> ComponentParts<Self> {
        let model = Self;
        let widgets = view_output!();
        ComponentParts { model, widgets }
    }

    fn update(&mut self, message: Self::Input, _sender: ComponentSender<Self>) {
        match message {}
    }
}
