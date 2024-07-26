use relm4::adw::prelude::*;
use relm4::prelude::*;

relm4::new_action_group!(pub MainMenuActions, "content");
relm4::new_stateless_action!(pub CreateMatch, MainMenuActions, "create_match");
relm4::new_stateless_action!(pub ConnectMatch, MainMenuActions, "connect_match");

pub struct MainMenuModel;
pub struct MainMenuInit;

#[derive(Debug)]
pub enum MainMenuInput {
    CreateMatch,
    ConnectMatch,
}

#[derive(Debug)]
pub enum MainMenuOutput {}

#[relm4::component(pub)]
impl SimpleComponent for MainMenuModel {
    type Init = MainMenuInit;
    type Input = MainMenuInput;
    type Output = MainMenuOutput;

    view! {
        #[root]
        gtk::Box {
            set_orientation: gtk::Orientation::Vertical,
            set_hexpand: true,
            set_margin_all: 32,

            gtk::Label {
                set_label: "Socket Trench",
                set_margin_all: 32,
                set_css_classes: &["title-1"],
            },
            adw::PreferencesGroup {
                adw::ActionRow {
                    set_title: "Criar partida",
                    set_activatable: true,
                    add_suffix = &gtk::Image {
                        set_icon_name: Some("go-next-symbolic")
                    },
                    connect_activated[sender] => move |_| {
                        sender.input(MainMenuInput::CreateMatch);
                    },
                },
                adw::ActionRow {
                    set_title: "Conectar à partida",
                    set_activatable: true,
                    add_suffix = &gtk::Image {
                        set_icon_name: Some("go-next-symbolic")
                    },
                    connect_activated[sender] => move |_| {
                        sender.input(MainMenuInput::ConnectMatch);
                    },
                },
            },
        }
    }

    fn init(
        _init: Self::Init,
        root: &Self::Root,
        sender: ComponentSender<Self>,
    ) -> ComponentParts<Self> {
        let model = Self;
        let widgets = view_output!();

        ComponentParts { model, widgets }
    }

    fn update(&mut self, message: Self::Input, _: ComponentSender<Self>) {
        match message {
            MainMenuInput::CreateMatch => println!("CreateMatch"),
            MainMenuInput::ConnectMatch => println!("ConnectMatch"),
        }
    }
}
