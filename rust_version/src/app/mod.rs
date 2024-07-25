use gtk::prelude::*;
use relm4::prelude::*;
use rust_i18n::t;

use crate::idle_client;

mod about;
mod actions;
mod content;
mod settings;

pub const APP_ID: &str = "com.joao_arthur.socket_trench";

pub struct AppModel {
    content: Controller<content::ContentModel>,
}

#[derive(Debug)]
pub enum AppInput {
    ShowAboutWindow,
}

#[derive(Debug)]
pub enum AppOutput {}

#[relm4::component(pub)]
impl SimpleComponent for AppModel {
    type Init = ();
    type Input = AppInput;
    type Output = AppOutput;

    menu! {
        primary_menu: {
            section! {
                &t!("about") => actions::ShowAbout,
            },
        }
    }

    view! {
        main_window = adw::ApplicationWindow {
            set_title: Some("Socket Trench"),
            add_css_class: "devel",

            gtk::Box {
                set_orientation: gtk::Orientation::Vertical,
                adw::HeaderBar {
                    pack_end = &gtk::MenuButton {
                        set_icon_name: "open-menu-symbolic",
                        set_menu_model: Some(&primary_menu),
                    },
                },
                model.content.widget(),
            }
        }
    }

    fn init(
        _init: Self::Init,
        window: &Self::Root,
        sender: ComponentSender<Self>,
    ) -> ComponentParts<Self> {
        let content = content::ContentModel::builder()
            .launch(content::ContentInit)
            .detach();
        let model = AppModel { content };
        let widgets = view_output!();
        Self::load_window_state(&widgets);
        Self::create_actions(&widgets, &sender);
        ComponentParts { model, widgets }
    }

    fn update(&mut self, message: Self::Input, _sender: ComponentSender<Self>) {
        use about;

        match message {
            Self::Input::ShowAboutWindow => {
               // let app = relm4::main_application();
               // let main_window = app
               //     .windows()
               //     .first()
               //     .expect("Event must be triggered by last focused window")
               //     .clone();
               // let about_window = about::Model::builder()
               //     .transient_for(&main_window)
               //     .launch(about::Init)
               //     .detach();
               // about_window.widget().present();
                idle_client::IdleClientModel::builder().launch(idle_client::IdleClientInit).widget().present();
            }
        }
    }

    fn shutdown(&mut self, widgets: &mut Self::Widgets, _output: relm4::Sender<Self::Output>) {
        println!("barbaridade");
        Self::save_window_state(&widgets);
    }
}
