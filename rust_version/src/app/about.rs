use relm4::prelude::*;

use crate::app;

pub struct Model;
pub struct Init;

#[derive(Debug)]
pub enum Input {}

#[derive(Debug)]
pub enum Output {}

#[relm4::component(pub)]
impl SimpleComponent for Model {
    type Init = Init;
    type Input = Input;
    type Output = Output;

    view! {
        adw::AboutWindow {
            set_application_icon: app::APP_ID,
            set_application_name: "Socket Trench",
            set_developer_name: "João Arthur",
            set_version: "1.0.0",
            set_website: "https://github.com/joao-arthur/socket_trench",
            set_issue_url: "https://github.com/joao-arthur/socket_trench/issues",
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
